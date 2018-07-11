package com.github.sigute.repobrowser.api.model

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class Repository(
        @SerializedName("name") val name: String,
        @SerializedName("owner") val owner: RepositoryOwner,
        @SerializedName("html_url") val htmlUrl: String,
        @SerializedName("description") val description: String?,
        @SerializedName("stargazers_count") val stargazersCount: Int,
        @SerializedName("forks_count") val forksCount: Int
) : Parcelable