package com.denisvieira05.githubusersearch.data.local.favoriteduser

import com.denisvieira05.githubusersearch.domain.model.UserDetail
import kotlinx.coroutines.flow.Flow

interface FavoritedUserLocalDataSource {
    suspend fun getAllFavoritedUsers(): Flow<List<UserDetail>>

    suspend fun removeFavoritedUser(remoteId: Long): Flow<Boolean>

    suspend fun saveAsFavoritedUser(user: UserDetail): Flow<Boolean>

    suspend fun findByRemoteId(remoteId: Long): Flow<UserDetail?>
}