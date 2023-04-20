package com.denisvieira05.githubusersearch.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDetail(
    val id: Long,
    val completeName: String,
    val userName: String,
    val avatarUrl: String?,
    val htmlUrl: String,
    val followers: Long,
    val following: Long,
    val repositories: Long,
    val blog: String?,
    val bio: String?,
    val twitterUsername: String?,
) : Parcelable