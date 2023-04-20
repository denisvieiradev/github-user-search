package com.denisvieira05.githubusersearch.domain.usecases

import com.denisvieira05.githubusersearch.data.remote.user.UserRemoteDataSource
import com.denisvieira05.githubusersearch.domain.model.DataOrException
import com.denisvieira05.githubusersearch.domain.model.SuggestedUser
import com.denisvieira05.githubusersearch.domain.model.UserDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUserDetailUseCase @Inject constructor(
    private val repository: UserRemoteDataSource,
) {
    suspend operator fun invoke(userName: String): DataOrException<UserDetail, Exception> {
        return withContext(Dispatchers.IO) {
            repository.getUserDetail(userName)
        }
    }
}