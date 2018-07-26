package com.github.sigute.player.ui.search

import com.github.sigute.player.api.GithubService
import com.github.sigute.player.api.model.SearchRepositoriesResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.ResourceSingleObserver
import io.reactivex.schedulers.Schedulers

class SearchPresenter(
        private val searchView: SearchView,
        private val repositoriesDataSource: GithubService) {

    fun searchTapped(query: String, sortType: SortType) {
        searchView.showLoading()

        repositoriesDataSource.searchRepositories(query, sortType.apiValue)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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