package com.denisvieira05.githubusersearch.data.remote.user.responses

import com.google.gson.annotations.SerializedName

data class UserDetailResponse (
    @SerializedName("id") val id: Long,
    @SerializedName("name") val completeName: String,
    @SerializedName("login") val userName: String,
    @SerializedName("avatar_url") val avatarUrl: String?,
    @SerializedName("html_url") val htmlUrl: String,
    @SerializedName("followers") val followersCount: Long,
    @SerializedName("following") val followingCount: Long,
    @SerializedName("public_repos") val repositoriesCount: Long,
    @SerializedName("blog") val blog: String?,
    @SerializedName("bio") val bio: String?,
    @SerializedName("twitter_username") val twitterUsername: String?,
)