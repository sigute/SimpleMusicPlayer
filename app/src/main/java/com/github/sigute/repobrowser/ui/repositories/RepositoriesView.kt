package com.github.sigute.repobrowser.ui.repositories

import com.github.sigute.repobrowser.api.model.Repository

interface RepositoriesView {
    fun showRepositories(repositories: List<Repository>)
    fun showRepository(repository: Repository)
}