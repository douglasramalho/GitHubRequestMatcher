package br.com.getupsolucoesdigitais.githubrequestmatcher.data.network.response;

import com.squareup.moshi.Json;

public class RepositoryResponse {

    @Json(name = "name")
    String name;

    @Json(name = "description")
    String description;

    @Json(name = "owner")
    OwnerResponse ownerResponse;

    public RepositoryResponse(String name, String description, OwnerResponse ownerResponse) {
        this.name = name;
        this.description = description;
        this.ownerResponse = ownerResponse;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public OwnerResponse getOwnerResponse() {
        return ownerResponse;
    }
}
