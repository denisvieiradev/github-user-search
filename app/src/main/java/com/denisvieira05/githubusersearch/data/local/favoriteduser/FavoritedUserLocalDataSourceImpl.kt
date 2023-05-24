package com.denisvieira05.githubusersearch.data.local.favoriteduser

import com.denisvieira05.githubusersearch.data.converters.UserDetailConverter
import com.denisvieira05.githubusersearch.domain.model.DataOrException
import com.denisvieira05.githubusersearch.domain.model.UserDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapNotNull

class FavoritedUserLocalDataSourceImpl(
    private val favoritedUserDAO: FavoritedUserDAO,
    private val userDetailConverter: UserDetailConverter,
) : FavoritedUserLocalDataSource {

    override fun getAllFavoritedUsers(): Flow<List<UserDetail>> =
        favoritedUserDAO.getAllFavoritedUsers().mapNotNull {
            userDetailConverter.mapFromEntityList(it)
        }

    override fun removeFavoritedUser(remoteId: Long): Flow<Boolean> = flow {
        favoritedUserDAO.removeFavoritedUser(remoteId)
        emit(true)
    }

    override fun saveAsFavoritedUser(user: UserDetail): Flow<Boolean> = flow {
        val favoritedUserEntity = userDetailConverter.mapToEntity(
            user = user
        )
        favoritedUserDAO.saveAsFavoritedUser(favoritedUserEntity)
        emit(true)
    }

    override fun findByRemoteId(remoteId: Long): Flow<UserDetail?> = flow {
        val favoritedUser = favoritedUserDAO.findByRemoteId(remoteId = remoteId)
        val user = userDetailConverter.mapFromEntity(favoritedUser)
        emit(user)
    }

}