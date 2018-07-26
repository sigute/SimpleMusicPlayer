package com.github.sigute.player.api.model

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class RepositoryOwner(
        @SerializedName("login") val login: String,
        @SerializedName("avatar_url") val avatarUrl: String
) : Parcelable