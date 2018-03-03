package br.com.getupsolucoesdigitais.githubrequestmatcher.ui.main;

import java.util.List;

import br.com.getupsolucoesdigitais.githubrequestmatcher.data.model.Repository;

public interface MainContract {

    interface View {

        void showLoading();

        void hideLoading();

        void displayRepositories(List<Repository> repositories);

        void displayApiError();

        void displayServerError();
    }

    interface Presenter {

        void onAttachView(View view);

        void getRepositories(String language, String sort, int page);

        void onDetachView();
    }
}
