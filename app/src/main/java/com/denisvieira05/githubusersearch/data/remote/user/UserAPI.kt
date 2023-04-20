package com.denisvieira05.githubusersearch.data.remote.user

import com.denisvieira05.githubusersearch.data.remote.user.responses.SuggestedUserResponse
import com.denisvieira05.githubusersearch.data.remote.user.responses.UserDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface UserAPI {
    @GET("users")
    suspend fun getUsers(
    ) : List<SuggestedUserResponse?>

    @GET("users/{userName}")
    suspend fun getUserDetail(
        @Path("userName") userName: String
    ) : UserDetailResponse?
}