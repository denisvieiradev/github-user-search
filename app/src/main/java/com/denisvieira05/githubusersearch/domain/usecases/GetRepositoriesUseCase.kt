package com.denisvieira05.githubusersearch.domain.usecases

import com.denisvieira05.githubusersearch.data.remote.repository.RepositoryRemoteDataSource
import com.denisvieira05.githubusersearch.data.remote.user.UserRemoteDataSource
import com.denisvieira05.githubusersearch.domain.model.DataOrException
import com.denisvieira05.githubusersearch.domain.model.Repository
import com.denisvieira05.githubusersearch.domain.model.SuggestedUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetRepositoriesUseCase @Inject constructor(
    private val repository: RepositoryRemoteDataSource,
) {
    suspend operator fun invoke(userName: String): DataOrException<List<Repository>, Exception> {
        return withContext(Dispatchers.IO) {
            repository.getRepositories(userName)
        }
    }
}