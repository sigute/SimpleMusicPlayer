package com.github.sigute.player.ui.repository

import com.github.sigute.player.api.model.Repository

class RepositoryPresenter(private val view: RepositoryView) {
    private var repository: Repository? = null

    fun setRepository(repository: Repository) {
        this.repository = repository
        view.showRepository(repository)

        //TODO request for subscribers would go here
    }

    fun getRepository(): Repository? {
        return repository
    }
}