package com.denisvieira05.githubusersearch.data.remote.retrofit.responses

import com.google.gson.annotations.SerializedName

data class UserItemListResponse (
    @SerializedName("id") val id: Long,
    @SerializedName("login") val userName: String,
    @SerializedName("avatar_url") val avatarUrl: String
)