package com.github.sigute.repobrowser.ui.repository

import com.github.sigute.repobrowser.api.model.Repository

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