package com.github.sigute.repobrowser.ui.search

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.github.sigute.repobrowser.R
import com.github.sigute.repobrowser.api.model.Repository
import com.github.sigute.repobrowser.api.model.SearchRepositoriesResponse
import com.github.sigute.repobrowser.di.DaggerWrapper
import com.github.sigute.repobrowser.ui.repositories.RepositoriesActivity
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_search.view.*
import kotlinx.coroutines.experimental.launch

class SearchActivity : AppCompatActivity(), SearchView {
    private val presenter by lazy { SearchPresenter(this, DaggerWrapper.component.githubService) }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setUpViews()
    }

    override fun onStop() {
        super.onStop()
        searchButton.reset()
    }

    private fun setUpViews() {
        searchButton.setOnClickListener {
            launch {
                presenter.searchTapped(searchTerm.text.toString(), sortSpinner.selectedItem as SortType)
            }
        }
        searchButton.isEnabled = false

        searchTerm.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //no action
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //no action
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchButton.isEnabled = s != null && s.isNotEmpty()
            }
        })

        sortSpinner.adapter = SearchSortSpinnerAdapter(this)
    }

    override fun showLoading() {
        runOnUiThread {
            searchTerm.isEnabled = false
            searchButton.startLoading()
        }
    }

    override fun showRepositories(repositories: List<Repository>) {
        runOnUiThread {
            searchButton.loadingSuccessful()
            searchTerm.isEnabled = true
            startActivity(RepositoriesActivity.getIntent(this, repositories))
        }
    }

    override fun showError(error: String) {
        runOnUiThread {
            searchTerm.isEnabled = true
            searchButton.loadingFailed()
            //TODO replace this with a nicer way to show errors
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }
    }
}
