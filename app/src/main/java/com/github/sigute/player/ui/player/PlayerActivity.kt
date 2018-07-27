package com.github.sigute.player.ui.player

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.sigute.player.R
import com.github.sigute.player.api.model.Track
import com.github.sigute.player.utils.loadTrackImage
import kotlinx.android.synthetic.main.activity_player.*
import android.media.AudioManager
import android.widget.Toast
import com.github.sigute.player.di.DaggerWrapper


class PlayerActivity : AppCompatActivity(), PlayerView {
    companion object {
        private const val EXTRA_TRACK = "EXTRA_TRACK"

        fun getIntent(context: Context, track: Track): Intent {
            val intent = Intent(context, PlayerActivity::class.java)
            intent.putExtra(EXTRA_TRACK, track)
            return intent
        }
    }

    private val presenter by lazy { PlayerPresenter(this) }
    private val mediaPlayer by lazy { DaggerWrapper.component.mediaPlayer }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        if (savedInstanceState != null) {
            presenter.setTrack(savedInstanceState.getParcelable(EXTRA_TRACK))
        } else {
            presenter.setTrack(intent.getParcelableExtra(EXTRA_TRACK))
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.let {
            outState.putParcelable(EXTRA_TRACK, presenter.getTrack())
        }
        super.onSaveInstanceState(outState)
    }

    override fun playTrack(track: Track) {
        trackName.text = track.title
        trackArtist.text = track.user.username
        loadTrackImage(this, track.artworkUrl, trackImage)

        //TODO this continues playing when navigating back and as such meets the spec
        // given more time, it could be replaced with background service, to ensure music continues even when activity finally gets destroyed
        try {
            val url = track.streamUrl
            mediaPlayer.reset()
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
            mediaPlayer.setDataSource(url)
            mediaPlayer.prepare()
            mediaPlayer.start()
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }
}