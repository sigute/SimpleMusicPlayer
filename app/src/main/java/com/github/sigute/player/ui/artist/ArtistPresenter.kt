package com.github.sigute.player.ui.artist

import com.github.sigute.player.api.HeartThisService
import com.github.sigute.player.api.model.Track
import com.github.sigute.player.api.model.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.ResourceSingleObserver
import io.reactivex.schedulers.Schedulers

class ArtistPresenter(private val view: ArtistView,
                      private val tracksDataSource: HeartThisService) {
    private var user: User? = null
    private var tracks = ArrayList<Track>()

    fun setUser(user: User) {
        this.user = user
        view.showUser(user)
    }

    fun getUser(): User {
        //TODO additional work needed to ensure no null
        return user!!
    }

    fun retrieveTracks() {
        view.showLoadingTracks()

        tracksDataSource
                .getTracksForArtist(user!!.permalink)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : ResourceSingleObserver<List<Track>>() {
                    override fun onSuccess(tracks: List<Track>) {
                        if (tracks.isEmpty()) {
                            //TODO use error from resources
                            view.showTracksError("No tracks found")
                            return
                        }

                        view.showTracks(tracks)
                    }

                    override fun onError(e: Throwable) {
                        //TODO use error from resources
                        view.showTracksError(e.message ?: "error")
                    }
                })
    }

    fun setTracks(tracks: List<Track>) {
        this.tracks = ArrayList(tracks)
        view.showTracks(tracks)
    }

    fun getTracks(): ArrayList<Track> {
        return tracks
    }

    fun trackSelected(track: Track) {
        //this method could have other things like tracking code, etc
        view.showTrack(track)
    }
}