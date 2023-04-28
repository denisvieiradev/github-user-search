package com.denisvieira05.githubusersearch.domain.usecases

import com.denisvieira05.githubusersearch.data.local.favoriteduser.FavoritedUserLocalDataSource
import com.denisvieira05.githubusersearch.data.remote.user.UserRemoteDataSource
import com.denisvieira05.githubusersearch.domain.model.DataOrException
import com.denisvieira05.githubusersearch.domain.model.SuggestedUser
import com.denisvieira05.githubusersearch.domain.model.UserDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CheckIsFavoritedUserUseCase @Inject constructor(
    private val localDataSource: FavoritedUserLocalDataSource,
) {
    suspend operator fun invoke(remoteId: Long): DataOrException<UserDetail, Exception> {
        return withContext(Dispatchers.IO) {
            localDataSource.findByRemoteId(remoteId)
        }
    }
}