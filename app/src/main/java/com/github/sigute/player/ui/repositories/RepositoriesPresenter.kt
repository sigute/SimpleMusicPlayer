package com.github.sigute.player.ui.repositories

import com.github.sigute.player.api.model.Repository

class RepositoriesPresenter(private val view: RepositoriesView) {
    private var repositories = ArrayList<Repository>()

    fun setRepositories(repositories: List<Repository>) {
        this.repositories = ArrayList(repositories)
        view.showRepositories(repositories)
    }

    fun getRepositories(): ArrayList<Repository> {
        return repositories
    }

    fun repositorySelected(repository: Repository) {
        //this method could have other things like tracking code, etc
        view.showRepository(repository)
    }
}