package com.denisvieira05.githubusersearch.data.local.favoriteduser

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

internal object FavoritedUser {
    const val tableName = "favorited_users"
}

@Entity(tableName = FavoritedUser.tableName)
data class FavoritedUserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "remote_id") val remoteId: Long,
    @ColumnInfo(name = "complete_name") val completeName: String,
    @ColumnInfo(name = "user_name") val userName: String,
    @ColumnInfo(name = "avatar_url") val avatarUrl: String?,
    @ColumnInfo(name = "html_url") val htmlUrl: String,
    val followers: Long,
    val following: Long,
    val repositoriesCount: Long,
    val blog: String?,
    val bio: String?,
    @ColumnInfo(name = "twitter_username") val twitterUsername: String?,
)