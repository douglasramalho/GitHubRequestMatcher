package br.com.getupsolucoesdigitais.githubrequestmatcher.data.mapper;

import java.util.ArrayList;
import java.util.List;

import br.com.getupsolucoesdigitais.githubrequestmatcher.data.model.Owner;
import br.com.getupsolucoesdigitais.githubrequestmatcher.data.model.Repository;
import br.com.getupsolucoesdigitais.githubrequestmatcher.data.network.response.RepositoryListResponse;
import br.com.getupsolucoesdigitais.githubrequestmatcher.data.network.response.RepositoryResponse;

public class RepositoryMapper {

    public static List<Repository> toRepositoryListModel(RepositoryListResponse repositoryListResponse) {
        List<Repository> repositoryList = new ArrayList<>();

        for (RepositoryResponse repositoryResponse : repositoryListResponse.getItemsResponse()) {
            final Repository repository = new Repository(
                    repositoryResponse.getName(),
                    repositoryResponse.getDescription(),
                    new Owner(
                            repositoryResponse.getOwnerResponse().getLogin(),
                            repositoryResponse.getOwnerResponse().getAvatarUrl()
                    )
            );

            repositoryList.add(repository);
        }

        return repositoryList;
    }
}
