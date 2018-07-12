package com.github.sigute.repobrowser.ui.repositories

import com.github.sigute.repobrowser.api.model.Repository

class RepositoriesPresenter(private val view: RepositoriesView) {
    private var repositories = ArrayList<Repository>()

    fun setRepositories(repositories: List<Repository>) {
        this.repositories = ArrayList(repositories)
        view.showRepositories(repositories)
    }

    fun getRepositories(): ArrayList<Repository> {
        return repositories
    }
}