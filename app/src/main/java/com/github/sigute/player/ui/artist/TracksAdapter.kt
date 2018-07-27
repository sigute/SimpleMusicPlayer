package com.github.sigute.player.ui.artist

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.github.sigute.player.api.model.Repository
import android.view.LayoutInflater
import com.github.sigute.player.R
import com.github.sigute.player.api.model.Track

class TracksAdapter : RecyclerView.Adapter<TrackHolder>() {
    private var tracks: List<Track> = ArrayList()
    private var delegate: TrackHolder.Companion.Delegate? = null

    fun setTracks(tracks: List<Track>, delegate: TrackHolder.Companion.Delegate?) {
        this.tracks = tracks
        this.delegate = delegate
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.holder_track, parent, false)
        return TrackHolder(view)
    }

    override fun onBindViewHolder(holder: TrackHolder, position: Int) {
        holder.setTrack(tracks[position], delegate)
    }

    override fun getItemCount(): Int = tracks.size
}