package br.com.getupsolucoesdigitais.githubrequestmatcher.ui.main;

import br.com.getupsolucoesdigitais.githubrequestmatcher.data.mapper.RepositoryMapper;
import br.com.getupsolucoesdigitais.githubrequestmatcher.data.network.ApiRepository;
import br.com.getupsolucoesdigitais.githubrequestmatcher.data.network.response.RepositoryListResponse;

public class MainPresenter implements MainContract.Presenter {

    private ApiRepository apiRepository;
    private MainContract.View view;

    public MainPresenter(ApiRepository apiRepository) {
        this.apiRepository = apiRepository;
    }

    @Override
    public void onAttachView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void getRepositories(String language, String sort, int page) {
        view.showLoading();

        apiRepository.getRepositories(
                language,
                sort,
                page,
                new ApiRepository.GetRepositoriesCallback() {
                    @Override
                    public void onSuccess(RepositoryListResponse repositoryListResponse) {
                        view.hideLoading();
                        view.displayRepositories(RepositoryMapper.toRepositoryListModel(repositoryListResponse));
                    }

                    @Override
                    public void onApiError(int code) {
                        view.hideLoading();
                        view.displayApiError();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        view.hideLoading();
                        view.displayServerError();
                    }
                });
    }

    @Override
    public void onDetachView() {
        this.view = null;
    }
}
