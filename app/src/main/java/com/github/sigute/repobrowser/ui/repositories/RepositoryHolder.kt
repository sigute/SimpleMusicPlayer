package com.github.sigute.repobrowser.ui.repositories

import android.support.v7.widget.RecyclerView
import android.view.View
import com.github.sigute.repobrowser.api.model.Repository
import com.github.sigute.repobrowser.utils.loadPersonImage
import kotlinx.android.synthetic.main.holder_repository.view.*

class RepositoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    companion object {
        interface Delegate {
            fun onRepositorySelected(repository: Repository)
        }
    }

    fun setRepository(repository: Repository, delegate: Delegate?) {
        itemView.repositoryName.text = repository.name
        itemView.repositoryForks.text = repository.forksCount.toString()
        itemView.repositoryStars.text = repository.stargazersCount.toString()

        if (repository.description != null) {
            itemView.repositoryDescription.text = repository.description
            itemView.repositoryDescription.visibility = View.VISIBLE
        } else {
            itemView.repositoryDescription.visibility = View.GONE
        }

        loadPersonImage(itemView.context, repository.owner.avatarUrl, itemView.repositoryOwnerAvatar)
        itemView.repositoryOwnerName.text = repository.owner.login

        itemView.setOnClickListener {
            delegate?.onRepositorySelected(repository)
        }
    }
}
