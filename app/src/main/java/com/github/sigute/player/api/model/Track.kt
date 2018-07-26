package com.github.sigute.player.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Track(
        @SerializedName("id") val id: String,
        @SerializedName("created_at") val createdAt: String,
        @SerializedName("user_id") val userId: String,
        @SerializedName("duration") val duration: String,
        @SerializedName("permalink") val permalink: String,
        @SerializedName("description") val description: String,
        @SerializedName("downloadable") val downloadable: String,
        @SerializedName("genre") val genre: String,
        @SerializedName("genre_slush") val genreSlush: String,
        @SerializedName("title") val title: String,
        @SerializedName("uri") val uri: String,
        @SerializedName("permalink_url") val permalinkUrl: String,
        @SerializedName("artwork_url") val artworkUrl: String,
        @SerializedName("background_url") val backgroundUrl: String,
        @SerializedName("waveform_data") val waveformData: String,
        @SerializedName("waveform_url") val waveformUrl: String,
        @SerializedName("user") val user: User,
        @SerializedName("stream_url") val streamUrl: String,
        @SerializedName("download_url") val downloadUrl: String,
        @SerializedName("playback_count") val playbackCount: String,
        @SerializedName("download_count") val downloadCount: String,
        @SerializedName("favoritings_count") val favoritingsCount: String,
        @SerializedName("favorited") val favorited: Boolean,
        @SerializedName("comment_count") val commentCount: String
) : Parcelable