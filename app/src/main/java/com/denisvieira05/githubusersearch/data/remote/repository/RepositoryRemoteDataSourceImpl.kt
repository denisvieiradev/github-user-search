package com.denisvieira05.githubusersearch.data.remote.repository

import com.denisvieira05.githubusersearch.data.converters.RepositoryConverter
import com.denisvieira05.githubusersearch.domain.model.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepositoryRemoteDataSourceImpl(
    private val api: RepositoryAPI,
    private val repositoryConverter: RepositoryConverter,
) : RepositoryRemoteDataSource {

    override fun getRepositories(userName: String): Flow<List<Repository>> = flow {
        val response = api.getRepositories(userName)
        emit(repositoryConverter.mapResponseList(response))
    }
}