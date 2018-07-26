package com.github.sigute.player.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
        @SerializedName("id") val id: String,
        @SerializedName("permalink") val permalink: String,
        @SerializedName("username") val username: String,
        @SerializedName("uri") val uri: String,
        @SerializedName("permalink_url") val permalinkUrl: String,
        @SerializedName("avatar_url") val avatarUrl: String
) : Parcelable