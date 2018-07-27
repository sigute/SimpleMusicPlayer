package com.github.sigute.player.ui.artists

import android.support.v7.widget.RecyclerView
import android.view.View
import com.github.sigute.player.api.model.User
import com.github.sigute.player.utils.loadPersonImage
import kotlinx.android.synthetic.main.holder_user.view.*

class ArtistHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    companion object {
        interface Delegate {
            fun onUserSelected(user: User)
        }
    }

    fun setRepository(user: User, delegate: Delegate?) {
        itemView.artistName.text = user.username

        loadPersonImage(itemView.context, user.avatarUrl, itemView.artistAvatar)

        itemView.setOnClickListener {
            delegate?.onUserSelected(user)
        }
    }
}
