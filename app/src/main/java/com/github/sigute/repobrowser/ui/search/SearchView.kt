package com.github.sigute.repobrowser.ui.search

import com.github.sigute.repobrowser.api.model.SearchRepositoriesResponse

interface SearchView {
    fun showLoading()
    fun showSearchResults(searchResults: SearchRepositoriesResponse)
    fun showError(error: String)
}