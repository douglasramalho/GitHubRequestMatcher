package br.com.getupsolucoesdigitais.githubrequestmatcher.ui.main;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.getupsolucoesdigitais.githubrequestmatcher.data.model.Repository;
import br.com.getupsolucoesdigitais.githubrequestmatcher.data.network.ApiRepository;
import br.com.getupsolucoesdigitais.githubrequestmatcher.data.network.response.OwnerResponse;
import br.com.getupsolucoesdigitais.githubrequestmatcher.data.network.response.RepositoryListResponse;
import br.com.getupsolucoesdigitais.githubrequestmatcher.data.network.response.RepositoryResponse;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock
    private MainContract.View view;

    @Mock
    private ApiRepository apiRepository;

    @Mock
    private RepositoryListResponse repositoryListResponse;

    @Captor
    private ArgumentCaptor<ApiRepository.GetRepositoriesCallback> argumentCaptor;

    private MainContract.Presenter presenter;

    @Before
    public void setUp() {
        presenter = new MainPresenter(apiRepository);
        presenter.onAttachView(view);
    }

    @Test
    public void getRepositories_whenOnSuccessCallback_shouldDisplayRepositories() {
        // Arrange
        final String language = "java";
        final String sort = "star";
        final int page = 1;

        final List<RepositoryResponse> repositoryResponseList = Collections.singletonList(
                new RepositoryResponse(
                        "Java",
                        "Descrição do repositório",
                        new OwnerResponse("Login do Proprietário", "http://url.da.imagem"))
        );

        when(repositoryListResponse.getItemsResponse()).thenReturn(repositoryResponseList);

        // Act
        presenter.getRepositories(language, sort, page);

        // Asserts
        verify(apiRepository).getRepositories(anyString(), anyString(), anyInt(), argumentCaptor.capture());
        argumentCaptor.getValue().onSuccess(repositoryListResponse);

        verify(view).showLoading(); verify(view).hideLoading();
        verify(view).displayRepositories(ArgumentMatchers.<Repository>anyList());
    }

    @After
    public void tearDown() {
        presenter.onDetachView();
    }
}