package com.denisvieira05.githubusersearch.data.local.favoriteduser

import com.denisvieira05.githubusersearch.data.converters.UserDetailConverter
import com.denisvieira05.githubusersearch.domain.model.DataOrException
import com.denisvieira05.githubusersearch.domain.model.UserDetail

class FavoritedUserLocalDataSourceImpl(
    private val favoritedUserDAO: FavoritedUserDAO,
    private val userDetailConverter: UserDetailConverter,
) : FavoritedUserLocalDataSource {

    override suspend fun getAllFavoritedUsers(): DataOrException<List<UserDetail>, Exception> {
        return try {
            DataOrException(
                data = favoritedUserDAO.getAllFavoritedUsers().map {
                    userDetailConverter.mapFromEntity(it)
                },
                exception = null
            )
        } catch (exception: Exception) {
            DataOrException(data = null, exception = exception)
        }
    }

    override suspend fun removeFavoritedUser(remoteId: Long): DataOrException<Boolean, Exception> {
        return try {
            favoritedUserDAO.removeFavoritedUser(remoteId)
            DataOrException(data = true, exception = null)
        } catch (exception: Exception) {
            DataOrException(data = null, exception = exception)
        }
    }

    override suspend fun saveAsFavoritedUser(user: UserDetail): DataOrException<Boolean, Exception>  {
        return try {
            val favoritedUserEntity = userDetailConverter.mapToEntity(
                user = user
            )
            favoritedUserDAO.saveAsFavoritedUser(favoritedUserEntity)
            DataOrException(data = true, exception = null)
        } catch (exception: Exception) {
            DataOrException(data = null, exception = exception)
        }
    }

    override suspend fun findByRemoteId(remoteId: Long): DataOrException<UserDetail, Exception> {
        return try {
            val favoritedUser = favoritedUserDAO.findByRemoteId(
                remoteId = remoteId
            )
            val user = userDetailConverter.mapFromEntity(favoritedUser)

            DataOrException(data = user, exception = null)
        } catch (exception: Exception) {
            DataOrException(data = null, exception = exception)
        }
    }

}