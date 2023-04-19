package com.denisvieira05.githubusersearch.data.remote.user

import com.denisvieira05.githubusersearch.data.remote.user.model.UserItemListResponse
import retrofit2.http.GET

interface UserAPI {

    @GET("users")
    suspend fun getUsers(
    ) : List<UserItemListResponse?>
}