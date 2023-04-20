package com.denisvieira05.githubusersearch.data.remote.repository

import com.denisvieira05.githubusersearch.data.converters.RepositoryConverter
import com.denisvieira05.githubusersearch.domain.model.DataOrException
import com.denisvieira05.githubusersearch.domain.model.Repository

class RepositoryRemoteDataSourceImpl(
    private val api: RepositoryAPI,
    private val repositoryConverter: RepositoryConverter,
) : RepositoryRemoteDataSource {

    override suspend fun getRepositories(userName: String): DataOrException<List<Repository>, Exception> {
        return try {
            val response = api.getRepositories(userName)
            DataOrException(data = repositoryConverter.mapResponse(response), exception = null)
        } catch (exception: Exception) {
            DataOrException(exception = exception, data = null)
        }
    }
}