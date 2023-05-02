package com.denisvieira05.githubusersearch.data.remote.repository

import com.denisvieira05.githubusersearch.domain.model.Repository
import kotlinx.coroutines.flow.Flow

interface RepositoryRemoteDataSource {
    fun getRepositories(userName: String): Flow<List<Repository>>
}