package com.github.sigute.player.ui.artists

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import com.github.sigute.player.R
import com.github.sigute.player.api.model.User


class ArtistsAdapter : RecyclerView.Adapter<ArtistHolder>() {
    private var users: List<User> = ArrayList()
    private var delegate: ArtistHolder.Companion.Delegate? = null

    fun setUsers(users: List<User>, delegate: ArtistHolder.Companion.Delegate?) {
        this.users = users
        this.delegate = delegate
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.holder_user, parent, false)
        return ArtistHolder(view)
    }

    override fun onBindViewHolder(holder: ArtistHolder, position: Int) {
        holder.setRepository(users[position], delegate)
    }

    override fun getItemCount(): Int = users.size
}