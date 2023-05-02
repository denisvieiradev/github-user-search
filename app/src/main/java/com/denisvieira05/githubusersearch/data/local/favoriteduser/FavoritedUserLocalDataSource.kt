package com.denisvieira05.githubusersearch.data.local.favoriteduser

import com.denisvieira05.githubusersearch.domain.model.UserDetail
import kotlinx.coroutines.flow.Flow

interface FavoritedUserLocalDataSource {
    fun getAllFavoritedUsers(): Flow<List<UserDetail>>

    fun removeFavoritedUser(remoteId: Long): Flow<Boolean>

    fun saveAsFavoritedUser(user: UserDetail): Flow<Boolean>

    fun findByRemoteId(remoteId: Long): Flow<UserDetail?>
}