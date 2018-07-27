package com.github.sigute.player.ui.artist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.github.sigute.player.R
import com.github.sigute.player.api.model.Track
import com.github.sigute.player.api.model.User
import com.github.sigute.player.di.DaggerWrapper
import com.github.sigute.player.ui.player.PlayerActivity
import kotlinx.android.synthetic.main.activity_artist.*

class ArtistActivity : AppCompatActivity(), ArtistView, TrackHolder.Companion.Delegate {
    companion object {
        private const val EXTRA_USER = "EXTRA_USER"
        private const val EXTRA_TRACKS = "EXTRA_TRACKS"

        fun getIntent(context: Context, user: User): Intent {
            val intent = Intent(context, ArtistActivity::class.java)
            intent.putExtra(EXTRA_USER, user)
            return intent
        }
    }

    private val presenter by lazy { ArtistPresenter(this, DaggerWrapper.component.heartThisService) }
    private val tracksAdapter by lazy { TracksAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist)

        setUpViews()

        if (savedInstanceState != null) {
            presenter.setUser(savedInstanceState.getParcelable(EXTRA_USER))
            presenter.setTracks(savedInstanceState.getParcelableArrayList(EXTRA_TRACKS))
        } else {
            presenter.setUser(intent.getParcelableExtra(EXTRA_USER))
            presenter.retrieveTracks()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.let {
            outState.putParcelable(EXTRA_USER, presenter.getUser())
            outState.putParcelableArrayList(EXTRA_TRACKS, presenter.getTracks())
        }
        super.onSaveInstanceState(outState)
    }

    private fun setUpViews() {
        tracks.layoutManager = LinearLayoutManager(this)
        tracks.adapter = tracksAdapter
    }

    override fun showUser(user: User) {
        //TODO this could be a nice header view with artist name, picture, followers, etc
        supportActionBar?.title = user.username
    }

    override fun showLoadingTracks() {
        //TODO
    }

    override fun showTracksError(error: String) {
        runOnUiThread {
            //TODO replace this with a nicer way to show errors
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }
    }

    override fun showTracks(tracks: List<Track>) {
        runOnUiThread {
            tracksAdapter.setTracks(tracks, this)
        }
    }

    override fun showTrack(track: Track) {
        runOnUiThread {
            startActivity(PlayerActivity.getIntent(this, track))
        }
    }

    override fun onTrackSelected(track: Track) {
        presenter.trackSelected(track)
    }
}
