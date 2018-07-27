package com.github.sigute.player.ui.player

import com.github.sigute.player.api.model.Track

class PlayerPresenter(var view: PlayerView) {
    private var track: Track? = null

    fun setTrack(track: Track) {
        this.track = track
        view.playTrack(track)
    }

    //TODO additional work to ensure non-null
    fun getTrack(): Track {
        return track!!
    }
}
