package com.github.sigute.repobrowser.ui.repositories

import com.github.sigute.repobrowser.api.model.Repository

class RepositoriesPresenter(private val view: RepositoriesView) {
    fun setRepositories(repositories: List<Repository>) {
        view.showRepositories(repositories)
    }
}