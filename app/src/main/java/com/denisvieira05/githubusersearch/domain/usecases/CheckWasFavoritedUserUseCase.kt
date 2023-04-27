package com.denisvieira05.githubusersearch.domain.usecases

import com.denisvieira05.githubusersearch.data.local.favoriteduser.FavoritedUserLocalDataSource
import com.denisvieira05.githubusersearch.domain.model.DataOrException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CheckWasFavoritedUserUseCase @Inject constructor(
    private val localDataSource: FavoritedUserLocalDataSource,
) {
    suspend operator fun invoke(remoteId: Long): DataOrException<Boolean, Exception> {
        return withContext(Dispatchers.IO) {
            val response = localDataSource.findByRemoteId(remoteId)
            val isFavorite = response.data != null

            DataOrException(isFavorite, response.exception)
        }
    }
}