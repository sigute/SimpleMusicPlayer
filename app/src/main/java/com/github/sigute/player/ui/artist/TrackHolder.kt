package com.github.sigute.player.ui.artist

import android.support.v7.widget.RecyclerView
import android.view.View
import com.github.sigute.player.api.model.Track
import com.github.sigute.player.utils.loadTrackImage
import kotlinx.android.synthetic.main.holder_track.view.*

class TrackHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    companion object {
        interface Delegate {
            fun onTrackSelected(track: Track)
        }
    }

    fun setTrack(track: Track, delegate: Delegate?) {
        itemView.trackName.text = track.title

        //TODO extract template to string resources
        //TODO nicer way to represent track length
        val duration = track.duration.toInt()
        val hours = duration / 3600
        val minutes = duration % 3600 / 60
        val seconds = duration % 60
        itemView.trackDuration.text = "$hours:$minutes:$seconds"

        loadTrackImage(itemView.context, track.artworkUrl, itemView.trackImage)

        itemView.setOnClickListener {
            delegate?.onTrackSelected(track)
        }
    }
}
