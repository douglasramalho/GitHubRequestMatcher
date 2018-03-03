package br.com.getupsolucoesdigitais.githubrequestmatcher.ui.main;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.concretesolutions.requestmatcher.InstrumentedTestRequestMatcherRule;
import br.com.concretesolutions.requestmatcher.RequestMatcherRule;
import br.com.concretesolutions.requestmatcher.model.HttpMethod;
import br.com.getupsolucoesdigitais.githubrequestmatcher.R;
import br.com.getupsolucoesdigitais.githubrequestmatcher.TestGitHubApplication;
import br.com.getupsolucoesdigitais.githubrequestmatcher.ui.main.MainActivity;
import br.com.getupsolucoesdigitais.githubrequestmatcher.ui.pullrequests.PullRequestsActivity;
import okhttp3.mockwebserver.MockResponse;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public IntentsTestRule<MainActivity> activityTestRule
            = new IntentsTestRule<>(
            MainActivity.class, true, false);

    @Rule
    public final RequestMatcherRule mockServer
            = new InstrumentedTestRequestMatcherRule();

    @Before
    public void setUp() {
        TestGitHubApplication app = (TestGitHubApplication) InstrumentationRegistry
                .getTargetContext().getApplicationContext();
        app.setBaseUrl(mockServer.url("/").toString());
    }

    @Test
    public void shouldDisplayRepositoriesWhenStatusCode200() {
        // Arrange
        mockServer.addFixture(200, "repositories.json")
                .ifRequestMatches()
                .pathIs("/search/repositories")
                .methodIs(HttpMethod.GET);

        activityTestRule.launchActivity(new Intent());

        // Act
        onView(ViewMatchers.withId(R.id.recycler_repositories))
                .perform(RecyclerViewActions.scrollToPosition(3));

        // Assert
        onView(withText("hmkcode")).check(matches(isDisplayed()));
    }

    @Test
    public void shouldDisplayRepositoriesWhenStatusCode500() {
        // Arrange
        mockServer.addFixture(500, "repositories.json")
                .ifRequestMatches()
                .pathIs("/search/repositories")
                .methodIs(HttpMethod.GET);

        // Act
        activityTestRule.launchActivity(new Intent());

        // Assert
        onView(withText("Erro ao obter lista de repositórios")).check(matches(isDisplayed()));
    }

    @Test
    public void shouldOpenPullRequestsScreenWhenItemIsClicked() {
        // Arrange
        mockServer.addFixture(200, "repositories.json")
                .ifRequestMatches()
                .pathIs("/search/repositories")
                .methodIs(HttpMethod.GET);

        activityTestRule.launchActivity(new Intent());

        // Act
        onView(withId(R.id.recycler_repositories))
                .perform(RecyclerViewActions.scrollToPosition(2), click());

        // Assert
        onView(withText("java")).check(matches(isDisplayed()));
    }

    @Test
    public void shouldValidatePullRequestsIntentWhenItemIsClicked() {
        // Arrange
        mockServer.addFixture(200, "repositories.json")
                .ifRequestMatches()
                .pathIs("/search/repositories")
                .methodIs(HttpMethod.GET);

        activityTestRule.launchActivity(new Intent());

        Intent resultData = new Intent();

        Instrumentation.ActivityResult activityResult
                = new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);

        intending(hasComponent(PullRequestsActivity.class.getName())).respondWith(activityResult);

        // Act
        onView(withId(R.id.recycler_repositories))
                .perform(RecyclerViewActions.scrollToPosition(2), click());

        // Assert
        intended(hasComponent(PullRequestsActivity.class.getName()));
    }
}