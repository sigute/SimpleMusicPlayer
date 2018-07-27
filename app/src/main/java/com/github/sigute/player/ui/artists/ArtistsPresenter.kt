package com.github.sigute.player.ui.artists

import com.github.sigute.player.api.HeartThisService
import com.github.sigute.player.api.model.Track
import com.github.sigute.player.api.model.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.ResourceSingleObserver
import io.reactivex.schedulers.Schedulers

class ArtistsPresenter(private val view: ArtistsView,
                       private val artistsDataSource: HeartThisService) {
    private var users = ArrayList<User>()

    fun retrieveArtists() {
        view.showLoading()

        artistsDataSource.getMainFeed()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : ResourceSingleObserver<List<Track>>() {
                    override fun onSuccess(tracks: List<Track>) {
                        if (tracks.isEmpty()) {
                            //TODO use error from resources
                            view.showError("No tracks found")
                            return
                        }

                        val popularUsers = tracks.map { it.user }.toSet()
                        users = ArrayList(popularUsers)

                        //TODO could retrieve an individual resource for each artist for pictures, followers, etc

                        view.showUsers(users)
                    }

                    override fun onError(e: Throwable) {
                        //TODO use error from resources
                        view.showError(e.message ?: "error")
                    }
                })
    }

    fun getUsers(): ArrayList<User> {
        return users
    }

    fun setUsers(repositories: List<User>) {
        this.users = ArrayList(repositories)
        view.showUsers(repositories)
    }

    fun userSelected(repository: User) {
        //this method could have other things like tracking code, etc
        view.showUser(repository)
    }
}