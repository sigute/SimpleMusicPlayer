package com.github.sigute.player.ui.repository

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.github.sigute.player.R
import com.github.sigute.player.api.model.Repository
import com.github.sigute.player.utils.loadPersonImage
import kotlinx.android.synthetic.main.activity_repository.*
import kotlinx.android.synthetic.main.holder_repository.view.*

class RepositoryActivity : AppCompatActivity(), RepositoryView {
    companion object {
        private const val EXTRA_REPOSITORY = "EXTRA_REPOSITORY"

        fun getIntent(context: Context, repository: Repository): Intent {
            val intent = Intent(context, RepositoryActivity::class.java)
            intent.putExtra(EXTRA_REPOSITORY, repository)
            return intent
        }
    }

    private val presenter by lazy { RepositoryPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)

        if (savedInstanceState != null) {
            presenter.setRepository(savedInstanceState.getParcelable(EXTRA_REPOSITORY))
        } else {
            presenter.setRepository(intent.getParcelableExtra(EXTRA_REPOSITORY))
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.let {
            outState.putParcelable(EXTRA_REPOSITORY, presenter.getRepository())
        }
        super.onSaveInstanceState(outState)
    }

    override fun showRepository(repository: Repository) {
        runOnUiThread {
            repositoryName.text = repository.name
            repositoryUrl.text = repository.htmlUrl
            repositoryForks.text = repository.forksCount.toString()
            repositoryStars.text = repository.stargazersCount.toString()
            repositoryWatchers.text = repository.watchersCount.toString()

            if (repository.description != null) {
                repositoryDescription.text = repository.description
                repositoryDescription.visibility = View.VISIBLE
            } else {
                repositoryDescription.visibility = View.GONE
            }

            loadPersonImage(this, repository.owner.avatarUrl, repositoryOwnerAvatar)
            repositoryOwnerName.text = repository.owner.login
        }
    }
}
