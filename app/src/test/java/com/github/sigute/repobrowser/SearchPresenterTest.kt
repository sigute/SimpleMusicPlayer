package com.github.sigute.repobrowser

import com.github.sigute.repobrowser.api.GithubService
import com.github.sigute.repobrowser.api.model.Repository
import com.github.sigute.repobrowser.api.model.RepositoryOwner
import com.github.sigute.repobrowser.api.model.SearchRepositoriesResponse
import com.github.sigute.repobrowser.ui.search.SearchPresenter
import com.github.sigute.repobrowser.ui.search.SearchView
import com.github.sigute.repobrowser.ui.search.SortType
import com.nhaarman.mockitokotlin2.then
import io.reactivex.Single
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit

class SearchPresenterTest {
    private lateinit var presenter: SearchPresenter

    @JvmField
    @Rule
    var mockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var mockSearchView: SearchView

    private val REPOSITORIES = listOf(
            Repository("Name",
                    RepositoryOwner("TestOwner", "www.google.com/avatar.gif"),
                    "www.google.com",
                    "Test Repo",
                    10,
                    2))
    private val SEARCH_RESULTS = SearchRepositoriesResponse(REPOSITORIES)

    private val mockRepositoriesDataSource: GithubService by lazy {
        object : GithubService {
            override fun searchRepositories(query: String, sort: String?, order: String): Single<SearchRepositoriesResponse> {
                return Single.just(SEARCH_RESULTS)
            }
        }
    }

    private val mockRepositoriesNoDataSource: GithubService by lazy {
        object : GithubService {
            override fun searchRepositories(query: String, sort: String?, order: String): Single<SearchRepositoriesResponse> {
                return Single.error(Exception("error!"))
            }
        }
    }

    @Test
    fun loadData() {
        presenter = SearchPresenter(mockSearchView, mockRepositoriesDataSource)

        runBlocking {
            presenter.searchTapped("searchQuery", SortType.BestMatch)
        }

        // then
        then(mockSearchView).should().showLoading()
        then(mockSearchView).should().showRepositories(REPOSITORIES)
    }

    @Test
    fun failedData() {
        presenter = SearchPresenter(mockSearchView, mockRepositoriesNoDataSource)

        runBlocking {
            presenter.searchTapped("searchQuery", SortType.BestMatch)
        }

        // then
        then(mockSearchView).should().showLoading()
        then(mockSearchView).should().showError("error!")
    }
}