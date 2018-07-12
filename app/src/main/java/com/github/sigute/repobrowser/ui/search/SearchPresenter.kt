package com.github.sigute.repobrowser.ui.search

import com.github.sigute.repobrowser.api.GithubService
import com.github.sigute.repobrowser.api.model.SearchRepositoriesResponse
import io.reactivex.observers.ResourceSingleObserver

class SearchPresenter(
        private val searchView: SearchView,
        private val repositoriesDataSource: GithubService) {

    suspend fun searchTapped(query: String, sortType: SortType) {
        searchView.showLoading()

        repositoriesDataSource.searchRepositories(query, sortType.apiValue)
                .subscribe(object : ResourceSingleObserver<SearchRepositoriesResponse>() {
                    override fun onSuccess(repositoriesResponse: SearchRepositoriesResponse) {
                        val repositories = repositoriesResponse.items
                        if (repositories.isEmpty()) {
                            searchView.showError("No repositories found")
                            return
                        }
                        searchView.showRepositories(repositories)
                    }

                    override fun onError(e: Throwable) {
                        //TODO use error from resources
                        searchView.showError(e.message ?: "error")
                    }
                })
    }
}