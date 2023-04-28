package com.denisvieira05.githubusersearch.domain.usecases

import com.denisvieira05.githubusersearch.data.remote.user.UserRemoteDataSource
import com.denisvieira05.githubusersearch.domain.model.DataOrException
import com.denisvieira05.githubusersearch.domain.model.SuggestedUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetSuggestedUsersUseCase @Inject constructor(
    private val repository: UserRemoteDataSource,
) {
    suspend operator fun invoke() = repository.getSuggestedUsers()
}