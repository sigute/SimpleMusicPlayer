package com.github.sigute.player.ui.artists

import com.github.sigute.player.api.model.User

interface ArtistsView {
    fun showLoading()
    fun showUsers(users: List<User>)
    fun showError(error: String)
    fun showUser(user: User)
}