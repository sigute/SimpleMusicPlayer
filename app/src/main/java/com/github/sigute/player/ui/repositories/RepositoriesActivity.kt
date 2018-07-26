package com.github.sigute.player.ui.repositories

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.github.sigute.player.R
import com.github.sigute.player.api.model.Repository
import com.github.sigute.player.ui.repository.RepositoryActivity
import kotlinx.android.synthetic.main.activity_repositories.*

class RepositoriesActivity : AppCompatActivity(), RepositoriesView, RepositoryHolder.Companion.Delegate {
    companion object {
        private const val EXTRA_REPOSITORIES = "EXTRA_REPOSITORIES"

        fun getIntent(context: Context, repositories: List<Repository>): Intent {
            val intent = Intent(context, RepositoriesActivity::class.java)
            intent.putParcelableArrayListExtra(EXTRA_REPOSITORIES, ArrayList(repositories))
            return intent
        }
    }

    private val presenter by lazy { RepositoriesPresenter(this) }
    private val repositoriesAdapter by lazy { RepositoriesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositories)

        setUpViews()

        if (savedInstanceState != null) {
            presenter.setRepositories(savedInstanceState.getParcelableArrayList(EXTRA_REPOSITORIES))
        } else {
            presenter.setRepositories(intent.getParcelableArrayListExtra(EXTRA_REPOSITORIES))
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.let {
            outState.putParcelableArrayList(EXTRA_REPOSITORIES, presenter.getRepositories())
        }
        super.onSaveInstanceState(outState)
    }

    private fun setUpViews() {
        repositories.layoutManager = LinearLayoutManager(this)
        repositories.adapter = repositoriesAdapter
    }

    override fun showRepositories(repositories: List<Repository>) {
        runOnUiThread {
            repositoriesAdapter.setRepositories(repositories, this)
        }
    }

    override fun showRepository(repository: Repository) {
        runOnUiThread {
            startActivity(RepositoryActivity.getIntent(this, repository))
        }
    }

    override fun onRepositorySelected(repository: Repository) {
        presenter.repositorySelected(repository)
    }
}
