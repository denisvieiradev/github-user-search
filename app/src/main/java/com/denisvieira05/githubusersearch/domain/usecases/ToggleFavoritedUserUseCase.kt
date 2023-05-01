package com.denisvieira05.githubusersearch.domain.usecases

import com.denisvieira05.githubusersearch.data.local.favoriteduser.FavoritedUserLocalDataSource
import com.denisvieira05.githubusersearch.domain.model.UserDetail
import com.denisvieira05.githubusersearch.domain.usecases.FavoritedActions.REMOVED_FAVORITE
import com.denisvieira05.githubusersearch.domain.usecases.FavoritedActions.SAVE_FAVORITE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

enum class FavoritedActions {
    SAVE_FAVORITE,
    REMOVED_FAVORITE
}

class ToggleFavoritedUserUseCase @Inject constructor(
    private val localDataSource: FavoritedUserLocalDataSource,
) {
    suspend operator fun invoke(user: UserDetail, isFavorited: Boolean): Flow<FavoritedActions> = flow {
        if (isFavorited) {
            localDataSource.removeFavoritedUser(remoteId = user.id).collect {
                emit(REMOVED_FAVORITE)
            }
        } else {
            localDataSource.saveAsFavoritedUser(user).collect {
                emit(SAVE_FAVORITE)
            }
        }
    }
}