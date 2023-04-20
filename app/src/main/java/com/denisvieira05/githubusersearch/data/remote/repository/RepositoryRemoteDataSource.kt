package com.denisvieira05.githubusersearch.data.remote.repository

import com.denisvieira05.githubusersearch.domain.model.DataOrException
import com.denisvieira05.githubusersearch.domain.model.Repository
import com.denisvieira05.githubusersearch.domain.model.SuggestedUser

interface RepositoryRemoteDataSource {
    suspend fun getRepositories(userName: String): DataOrException<List<Repository>, Exception>
}