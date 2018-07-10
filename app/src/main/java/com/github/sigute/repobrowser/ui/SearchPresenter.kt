package com.github.sigute.repobrowser.ui

import com.github.sigute.repobrowser.api.GithubService
import com.github.sigute.repobrowser.api.model.SearchRepositoriesResponse
import io.reactivex.observers.ResourceSingleObserver

class SearchPresenter(
        private val searchView: SearchView,
        private val repositoriesDataSource: GithubService) {

    suspend fun searchTapped(query: String) {
        searchView.showLoading()

        repositoriesDataSource.searchRepositories(query, null)
                .subscribe(object : ResourceSingleObserver<SearchRepositoriesResponse>() {
                    override fun onSuccess(repositoriesResponse: SearchRepositoriesResponse) {
                        searchView.showSearchResults(repositoriesResponse)
                        //TODO could show different stuff if results empty
                    }

                    override fun onError(e: Throwable) {
                        //TODO use error from resources
                        searchView.showError(e.message ?: "error")
                    }
                })
    }
}