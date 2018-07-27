package com.github.sigute.player.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Artist(
        @SerializedName("id") val id: String,
        @SerializedName("permalink") val permalink: String,
        @SerializedName("username") val username: String,
        @SerializedName("uri") val uri: String,
        @SerializedName("permalink_url") val permalinkUrl: String,
        @SerializedName("avatar_url") val avatarUrl: String,
        @SerializedName("background_url") val backgroundUrl: String,
        @SerializedName("description") val description: String,
        @SerializedName("track_count") val trackCount: Int,
        @SerializedName("playlist_count") val playlistCount: Int,
        @SerializedName("likes_count") val likesCount: Int,
        @SerializedName("followers_count") val followersCount: Int,
        @SerializedName("following") val following: Boolean
) : Parcelable