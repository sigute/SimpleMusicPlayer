package com.github.sigute.player.api

import com.github.sigute.player.api.model.Artist
import com.github.sigute.player.api.model.Track
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HeartThisService {
    @GET("/feed")
    fun getMainFeed(
            @Query("page") page: Int = 1,
            @Query("count") count: Int = 20,
            @Query("type") type: String = "popular")
            : Single<List<Track>>

    @GET("/{artistPermalink}")
    fun getArtist(
            @Path("artistPermalink") artistPermalink: String
    ): Single<Artist>

    @GET("/{artistPermalink}/?type=tracks")
    fun getTracksForArtist(
            @Path("artistPermalink") artistPermalink: String,
            @Query("page") page: Int = 1,
            @Query("count") count: Int = 20)
            : Single<List<Track>>
}
