package br.com.getupsolucoesdigitais.githubrequestmatcher;

import android.app.Application;

public class GitHubApplication extends Application {

    public String getBaseUrl() {
        return "https://api.github.com/";
    }
}
