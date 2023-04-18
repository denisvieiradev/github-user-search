package com.denisvieira05.githubusersearch.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserItemList(
    val id: Long,
    val userName: String,
    val avatarUrl: String
) : Parcelable