package com.satya.profilesearchapp.data.api

import com.satya.profilesearchapp.data.model.GHRepo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi {

    @GET("/search/repositories")
    suspend fun getUserRepository(
        @Query("q") language: String = "language:swift",
        @Query("sort") sort: String = "stars",
        @Query("order") order: String = "desc"
    ): GHRepo

}