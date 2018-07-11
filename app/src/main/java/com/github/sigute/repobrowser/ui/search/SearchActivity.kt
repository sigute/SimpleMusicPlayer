package com.github.sigute.repobrowser.ui.search

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.github.sigute.repobrowser.R
import com.github.sigute.repobrowser.api.model.SearchRepositoriesResponse
import com.github.sigute.repobrowser.di.DaggerWrapper
import com.github.sigute.repobrowser.ui.repositories.RepositoriesActivity
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.coroutines.experimental.launch

class SearchActivity : AppCompatActivity(), SearchView {
    private val presenter by lazy { SearchPresenter(this, DaggerWrapper.component.githubService) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setUpViews()

        if (savedInstanceState != null) {
            //TODO restore state
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.let {
            //TODO save state
        }
        super.onSaveInstanceState(outState)
    }

    private fun setUpViews() {
        searchButton.setOnClickListener {
            launch {
                presenter.searchTapped(searchTerm.text.toString())
            }
        }
    }

    override fun showLoading() {
        runOnUiThread {
            searchTerm.isEnabled = false
            searchButton.isEnabled = false
            //TODO add spinner
        }
    }

    override fun showSearchResults(searchResults: SearchRepositoriesResponse) {
        runOnUiThread {
            searchTerm.isEnabled = true
            searchButton.isEnabled = true
            startActivity(RepositoriesActivity.getIntent(this, searchResults.items))
        }
    }

    override fun showError(error: String) {
        runOnUiThread {
            searchTerm.isEnabled = true
            searchButton.isEnabled = true
            //TODO
        }
    }
}
