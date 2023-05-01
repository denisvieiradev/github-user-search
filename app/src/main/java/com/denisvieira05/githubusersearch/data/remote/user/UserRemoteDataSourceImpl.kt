package com.denisvieira05.githubusersearch.data.remote.user

import com.denisvieira05.githubusersearch.data.converters.SuggestedUserConverter
import com.denisvieira05.githubusersearch.data.converters.UserDetailConverter
import com.denisvieira05.githubusersearch.domain.model.DataOrException
import com.denisvieira05.githubusersearch.domain.model.SuggestedUser
import com.denisvieira05.githubusersearch.domain.model.UserDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRemoteDataSourceImpl(
    private val api: UserAPI,
    private val suggestedUserConverter: SuggestedUserConverter,
    private val userDetailConverter: UserDetailConverter,
) : UserRemoteDataSource {

    override suspend fun getSuggestedUsers(): Flow<List<SuggestedUser>> = flow {
        val response = api.getUsers()
        emit(suggestedUserConverter.mapResponse(response))
    }

    override suspend fun getUserDetail(userName: String): Flow<UserDetail> = flow {
        val response = api.getUserDetail(userName)
        emit(userDetailConverter.mapFromResponse(response))
    }
}