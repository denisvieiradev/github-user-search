package com.denisvieira05.githubusersearch.data.local.favoriteduser

import com.denisvieira05.githubusersearch.data.converters.UserDetailConverter
import com.denisvieira05.githubusersearch.domain.model.DataOrException
import com.denisvieira05.githubusersearch.domain.model.UserDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FavoritedUserLocalDataSourceImpl(
    private val favoritedUserDAO: FavoritedUserDAO,
    private val userDetailConverter: UserDetailConverter,
) : FavoritedUserLocalDataSource {

    override suspend fun getAllFavoritedUsers(): Flow<List<UserDetail>> = flow {
        val result = favoritedUserDAO.getAllFavoritedUsers().mapNotNull {
            userDetailConverter.mapFromEntity(it)
        }
        emit(result)
    }

    override suspend fun removeFavoritedUser(remoteId: Long): Flow<Boolean> = flow {
        favoritedUserDAO.removeFavoritedUser(remoteId)
        emit(true)
    }

    override suspend fun saveAsFavoritedUser(user: UserDetail): Flow<Boolean> = flow {
        val favoritedUserEntity = userDetailConverter.mapToEntity(
            user = user
        )
        favoritedUserDAO.saveAsFavoritedUser(favoritedUserEntity)
        emit(true)
    }

    override suspend fun findByRemoteId(remoteId: Long): Flow<UserDetail?> = flow {
        val favoritedUser = favoritedUserDAO.findByRemoteId(remoteId = remoteId)
        val user = userDetailConverter.mapFromEntity(favoritedUser)
        emit(user)
    }

}