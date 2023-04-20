package com.denisvieira05.githubusersearch.data.remote.repository

import com.denisvieira05.githubusersearch.data.remote.repository.responses.RepositoryResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RepositoryAPI {

    @GET("users/{userName}/repos")
    suspend fun getRepositories(
        @Path("userName") userName: String
    ) : List<RepositoryResponse?>
}