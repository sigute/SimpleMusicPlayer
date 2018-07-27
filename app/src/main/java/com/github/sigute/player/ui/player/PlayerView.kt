package com.github.sigute.player.ui.player

import com.github.sigute.player.api.model.Track

interface PlayerView {
    fun playTrack(track: Track)
}
