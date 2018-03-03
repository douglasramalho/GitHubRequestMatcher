package br.com.getupsolucoesdigitais.githubrequestmatcher.data.network;

import android.support.design.widget.Snackbar;
import android.view.View;

import br.com.getupsolucoesdigitais.githubrequestmatcher.GitHubApplication;
import br.com.getupsolucoesdigitais.githubrequestmatcher.R;
import br.com.getupsolucoesdigitais.githubrequestmatcher.data.mapper.RepositoryMapper;
import br.com.getupsolucoesdigitais.githubrequestmatcher.data.network.response.RepositoryListResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiDataSource implements ApiRepository {

    private GitHubEndPoint apiEndPoint;

    public ApiDataSource(GitHubEndPoint apiEndPoint) {
        this.apiEndPoint = apiEndPoint;
    }

    @Override
    public void getRepositories(String language, String sort, int page, final GetRepositoriesCallback callback) {
        final Call<RepositoryListResponse> call
                = apiEndPoint.getRepositories(language, sort, page);

        call.enqueue(new Callback<RepositoryListResponse>() {
            @Override
            public void onResponse(Call<RepositoryListResponse> call, Response<RepositoryListResponse> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onApiError(response.code());
                }
            }

            @Override
            public void onFailure(Call<RepositoryListResponse> call, Throwable t) {
                callback.onError(t);
            }
        });
    }
}
