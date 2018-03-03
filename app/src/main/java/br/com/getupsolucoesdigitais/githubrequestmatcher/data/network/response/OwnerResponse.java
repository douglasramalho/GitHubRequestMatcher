package br.com.getupsolucoesdigitais.githubrequestmatcher.data.network.response;

import com.squareup.moshi.Json;

public class OwnerResponse {

    @Json(name = "login")
    String login;

    @Json(name = "avatar_url")
    String avatarUrl;

    public OwnerResponse(String login, String avatarUrl) {
        this.login = login;
        this.avatarUrl = avatarUrl;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }
}
