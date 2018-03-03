package br.com.getupsolucoesdigitais.githubrequestmatcher.data.network.response;

import com.squareup.moshi.Json;

import java.util.List;

public class RepositoryListResponse {

    @Json(name = "items")
    List<RepositoryResponse> itemsResponse;

    public List<RepositoryResponse> getItemsResponse() {
        return itemsResponse;
    }
}
