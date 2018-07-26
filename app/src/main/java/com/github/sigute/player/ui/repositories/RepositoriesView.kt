package com.github.sigute.player.ui.repositories

import com.github.sigute.player.api.model.Repository

interface RepositoriesView {
    fun showRepositories(repositories: List<Repository>)
    fun showRepository(repository: Repository)
}