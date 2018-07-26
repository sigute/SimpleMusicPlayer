package com.github.sigute.player.ui.search

import com.github.sigute.player.api.model.Repository

interface SearchView {
    fun showLoading()
    fun showRepositories(repositories: List<Repository>)
    fun showError(error: String)
}