package br.com.getupsolucoesdigitais.githubrequestmatcher.ui.pullrequests;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import br.com.getupsolucoesdigitais.githubrequestmatcher.R;

public class PullRequestsActivity extends AppCompatActivity {

    private static final String EXTRA_REPOSITORY_NAME = "EXTRA_REPOSITORY_NAME";

    private TextView textRepositoryName;

    public static Intent getStartIntent(Context context, String repositoryName) {
        return new Intent(context, PullRequestsActivity.class)
                .putExtra(EXTRA_REPOSITORY_NAME, repositoryName);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_requests);

        textRepositoryName = findViewById(R.id.repository_name);
        textRepositoryName.setText(getIntent().getStringExtra(EXTRA_REPOSITORY_NAME));
    }
}
