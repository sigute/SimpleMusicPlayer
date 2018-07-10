package com.github.sigute.repobrowser.api

import com.github.sigute.repobrowser.api.model.SearchRepositoriesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("/search/repositories")
    fun searchRepositories(
            @Path("q") query: String,
            @Path("sort") sort: String?,
            @Path("order") order: String = "desc")
            : SearchRepositoriesResponse
}
