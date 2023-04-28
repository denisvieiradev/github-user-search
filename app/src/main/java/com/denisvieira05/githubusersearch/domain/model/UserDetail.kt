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
    val followersCount: Long,
    val followingCount: Long,
    val repositoriesCount: Long,
    val blog: String?,
    val bio: String?,
    val twitterUsername: String?,
) : Parcelable