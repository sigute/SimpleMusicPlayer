package com.github.sigute.player.api

import com.github.sigute.player.api.model.SearchRepositoriesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {
    @GET("/search/repositories")
    fun searchRepositories(
            @Query("q") query: String,
            @Query("sort") sort: String?,
            @Query("order") order: String = "desc")
            : Single<SearchRepositoriesResponse>
}
