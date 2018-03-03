package br.com.getupsolucoesdigitais.githubrequestmatcher.ui.main;

import android.os.Parcelable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import br.com.getupsolucoesdigitais.githubrequestmatcher.GitHubApplication;
import br.com.getupsolucoesdigitais.githubrequestmatcher.R;
import br.com.getupsolucoesdigitais.githubrequestmatcher.data.model.Repository;
import br.com.getupsolucoesdigitais.githubrequestmatcher.data.network.ApiDataSource;
import br.com.getupsolucoesdigitais.githubrequestmatcher.data.network.ApiService;
import br.com.getupsolucoesdigitais.githubrequestmatcher.ui.pullrequests.PullRequestsActivity;

public class MainActivity extends AppCompatActivity
        implements MainContract.View, MainAdapter.OnItemClickListener {

    private static final String BUNDLE_REPOSITORY_LIST = "BUNDLE_REPOSITORY_LIST";

    private CoordinatorLayout coordinatorMain;
    private Toolbar toolbarMain;
    private ProgressBar progressRepositories;
    private RecyclerView recyclerView;

    private List<Repository> repositoryList = new ArrayList<>();

    private MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        setUpToolbar();
        initPresenter();

        if (savedInstanceState != null) {
            repositoryList = savedInstanceState.getParcelableArrayList(BUNDLE_REPOSITORY_LIST);
            setUpRepositoryAdapter();
        } else {
            presenter.getRepositories("java", "star", 1);
        }
    }

    private void initPresenter() {
        GitHubApplication app = ((GitHubApplication) getApplicationContext());
        presenter = new MainPresenter(
                new ApiDataSource(ApiService.getInstance(app.getBaseUrl())));
        presenter.onAttachView(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(BUNDLE_REPOSITORY_LIST, (ArrayList<? extends Parcelable>) repositoryList);
        super.onSaveInstanceState(outState);
    }

    private void bindViews() {
        coordinatorMain = findViewById(R.id.coordinator_main);
        toolbarMain = findViewById(R.id.toolbar_main);
        progressRepositories = findViewById(R.id.progress_repositories);
        recyclerView = findViewById(R.id.recycler_repositories);
    }

    private void setUpToolbar() {
        setSupportActionBar(toolbarMain);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.repositories));
        }
    }

    private void setUpRepositoryAdapter() {
        final MainAdapter mainAdapter
                = new MainAdapter(repositoryList, this);

        final LinearLayoutManager layoutManager
                = new LinearLayoutManager(MainActivity.this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mainAdapter);
    }

    @Override
    public void showLoading() {
        progressRepositories.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressRepositories.setVisibility(View.GONE);
    }

    @Override
    public void displayRepositories(List<Repository> repositories) {
        repositoryList.addAll(repositories);
        setUpRepositoryAdapter();
    }

    @Override
    public void displayApiError() {
        Snackbar.make(coordinatorMain, getString(R.string.repositories_error), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void displayServerError() {
        Snackbar.make(coordinatorMain, getString(R.string.repositories_error), Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        presenter.onDetachView();
        super.onDestroy();
    }

    @Override
    public void onItemClicked(Repository repository) {
        startActivity(PullRequestsActivity.getStartIntent(this, repository.getName()));
    }
}
