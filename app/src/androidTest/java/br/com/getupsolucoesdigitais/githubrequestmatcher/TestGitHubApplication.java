package br.com.getupsolucoesdigitais.githubrequestmatcher;

public class TestGitHubApplication extends GitHubApplication {

    private String baseUrl;

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public String getBaseUrl() {
        return baseUrl;
    }
}
