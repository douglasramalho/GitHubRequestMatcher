package br.com.getupsolucoesdigitais.githubrequestmatcher.data.network;

import br.com.getupsolucoesdigitais.githubrequestmatcher.data.network.response.RepositoryListResponse;

public interface ApiRepository {

    interface GetRepositoriesCallback {
        void onSuccess(RepositoryListResponse repositoryListResponse);

        void onError(Throwable throwable);

        void onApiError(int code);
    }

    void getRepositories(String language, String sort, int page, GetRepositoriesCallback callback);
}
