package com.github.sigute.repobrowser.ui.search

import com.github.sigute.repobrowser.api.model.Repository

interface SearchView {
    fun showLoading()
    fun showRepositories(repositories: List<Repository>)
    fun showError(error: String)
}