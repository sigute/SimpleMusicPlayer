package com.github.sigute.player.ui.repository

import com.github.sigute.player.api.model.Repository

interface RepositoryView {
    fun showRepository(repository: Repository)
}