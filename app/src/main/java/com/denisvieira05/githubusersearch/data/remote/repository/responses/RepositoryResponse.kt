package com.denisvieira05.githubusersearch.data.remote.repository.responses

import com.google.gson.annotations.SerializedName

data class RepositoryResponse (
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("html_url") val htmlUrl: String,
    @SerializedName("forks_count") val forks: Long,
    @SerializedName("stargazers_count") val stars: Long,
    @SerializedName("language") val language: String,
)