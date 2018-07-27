package com.github.sigute.player.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.sigute.player.R

fun loadPersonImage(context: Context, url: String, imageView: ImageView) {
    Glide.with(context)
            .setDefaultRequestOptions(RequestOptions().apply {
                placeholder(R.drawable.ic_avatar_placeholder)
                error(R.drawable.ic_avatar_placeholder)
            })
            .load(url)
            .into(imageView)
}

fun loadTrackImage(context: Context, url: String, imageView: ImageView) {
    Glide.with(context)
            .setDefaultRequestOptions(RequestOptions().apply {
                placeholder(R.drawable.ic_track)
                error(R.drawable.ic_track)
            })
            .load(url)
            .into(imageView)
}