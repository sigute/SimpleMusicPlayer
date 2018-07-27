package com.github.sigute.player.ui.artists

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.github.sigute.player.R
import com.github.sigute.player.api.model.User
import com.github.sigute.player.di.DaggerWrapper
import kotlinx.android.synthetic.main.activity_artists.*

class ArtistsActivity : AppCompatActivity(), ArtistsView, ArtistHolder.Companion.Delegate {
    companion object {
        private const val EXTRA_USERS = "EXTRA_USERS"
    }

    private val presenter by lazy { ArtistsPresenter(this, DaggerWrapper.component.heartThisService) }
    private val usersAdapter by lazy { ArtistsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artists)

        setUpViews()

        if (savedInstanceState != null) {
            presenter.setUsers(savedInstanceState.getParcelableArrayList(EXTRA_USERS))
        } else {
            presenter.retrieveArtists()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.let {
            outState.putParcelableArrayList(EXTRA_USERS, presenter.getUsers())
        }
        super.onSaveInstanceState(outState)
    }

    private fun setUpViews() {
        artists.layoutManager = LinearLayoutManager(this)
        artists.adapter = usersAdapter
    }

    override fun showUsers(users: List<User>) {
        runOnUiThread {
            usersAdapter.setUsers(users, this)
        }
    }

    override fun showUser(user: User) {
        runOnUiThread {
            //TODO
            //startActivity(RepositoryActivity.getIntent(this, user))
        }
    }

    override fun showLoading() {
        //TODO show spinner
    }

    override fun showError(error: String) {
        runOnUiThread {
            //TODO replace this with a nicer way to show errors
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onUserSelected(user: User) {
        presenter.userSelected(user)
    }
}
