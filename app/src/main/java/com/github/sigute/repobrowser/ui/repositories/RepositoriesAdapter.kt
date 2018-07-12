package com.github.sigute.repobrowser.ui.repositories

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.github.sigute.repobrowser.api.model.Repository
import android.view.LayoutInflater
import com.github.sigute.repobrowser.R


class RepositoriesAdapter : RecyclerView.Adapter<RepositoryHolder>() {
    private var repositories: List<Repository> = ArrayList()
    private var delegate: RepositoryHolder.Companion.Delegate? = null

    fun setRepositories(repositories: List<Repository>, delegate: RepositoryHolder.Companion.Delegate?) {
        this.repositories = repositories
        this.delegate = delegate
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.holder_repository, parent, false)
        return RepositoryHolder(view)
    }

    override fun onBindViewHolder(holder: RepositoryHolder, position: Int) {
        holder.setRepository(repositories[position], delegate)
    }

    override fun getItemCount(): Int = repositories.size
}