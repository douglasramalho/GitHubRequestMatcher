package br.com.getupsolucoesdigitais.githubrequestmatcher.data.network;

import br.com.getupsolucoesdigitais.githubrequestmatcher.data.network.response.RepositoryListResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GitHubEndPoint {

    @GET("search/repositories")
    Call<RepositoryListResponse> getRepositories(
            @Query("q") String language,
            @Query("sort") String sort,
            @Query("page") int page
    );
}
