package com.denisvieira05.githubusersearch.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Repository(
    val id: Long,
    val name: String,
    val description: String?,
    val htmlUrl: String,
    val forks: Long,
    val language: String?,
    val stars: Long,
) : Parcelable