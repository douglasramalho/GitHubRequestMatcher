package br.com.getupsolucoesdigitais.githubrequestmatcher.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ApiService {

    private static GitHubEndPoint gitHubEndPoint;

    private ApiService() {
    }

    public static GitHubEndPoint getInstance(String baseUrl) {
        if (gitHubEndPoint == null) {
            gitHubEndPoint = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()
                    .create(GitHubEndPoint.class);
        }

        return gitHubEndPoint;
    }


}
