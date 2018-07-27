package com.github.sigute.player.ui.artist

import com.github.sigute.player.api.model.Track
import com.github.sigute.player.api.model.User

interface ArtistView {
    fun showUser(user: User)
    fun showLoadingTracks()
    fun showTracksError(error: String)
    fun showTracks(tracks: List<Track>)
    fun showTrack(track: Track)
}