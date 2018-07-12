package com.github.sigute.repobrowser.ui.repository

import com.github.sigute.repobrowser.api.model.Repository

interface RepositoryView {
    fun showRepository(repository: Repository)
}