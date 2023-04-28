package com.denisvieira05.githubusersearch.data.local.favoriteduser

import com.denisvieira05.githubusersearch.domain.model.DataOrException
import com.denisvieira05.githubusersearch.domain.model.UserDetail

interface FavoritedUserLocalDataSource {
    suspend fun getAllFavoritedUsers(): DataOrException<List<UserDetail>, Exception>

    suspend fun removeFavoritedUser(remoteId: Long): DataOrException<Boolean, Exception>

    suspend fun saveAsFavoritedUser(user: UserDetail): DataOrException<Boolean, Exception>

    suspend fun findByRemoteId(remoteId: Long): DataOrException<UserDetail, Exception>
}