package com.github.sigute.player.api

import com.github.sigute.player.api.model.SearchRepositoriesResponse
import com.github.sigute.player.api.model.Track
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HeartThisService {
    //TODO remove this
    @GET("/search/repositories")
    fun searchRepositories(
            @Query("q") query: String,
            @Query("sort") sort: String?,
            @Query("order") order: String = "desc")
            : Single<SearchRepositoriesResponse>

    @GET("/feed")
    fun getMainFeed(
            @Query("page") page: Int = 1,
            @Query("count") count: Int = 20,
            @Query("type") type: String = "popular")
            : Single<List<Track>>

    @GET("/{artistPermalink}/?type=tracks")
    fun getTracksForArtist(
            @Path("artistPermalink") artistPermalink: String,
            @Query("page") page: Int = 1,
            @Query("count") count: Int = 20)
            : Single<List<Track>>
}
