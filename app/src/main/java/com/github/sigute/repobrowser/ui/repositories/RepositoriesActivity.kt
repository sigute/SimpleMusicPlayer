package com.github.sigute.repobrowser.ui.repositories

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.github.sigute.repobrowser.R
import com.github.sigute.repobrowser.api.model.Repository
import kotlinx.android.synthetic.main.activity_repositories.*

class RepositoriesActivity : AppCompatActivity(), RepositoriesView {
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
            //TODO restore state
        } else {
            presenter.setRepositories(intent.getParcelableArrayListExtra(EXTRA_REPOSITORIES))
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.let {
            //TODO save state
        }
        super.onSaveInstanceState(outState)
    }

    private fun setUpViews() {
        repositories.layoutManager = LinearLayoutManager(this)
        repositories.adapter = repositoriesAdapter
    }

    override fun showRepositories(repositories: List<Repository>) {
        runOnUiThread {
            repositoriesAdapter.setRepositories(repositories)
        }
    }
}
