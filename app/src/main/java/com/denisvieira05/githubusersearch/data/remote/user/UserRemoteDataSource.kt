package com.denisvieira05.githubusersearch.data.remote.user

import com.denisvieira05.githubusersearch.domain.model.DataOrException
import com.denisvieira05.githubusersearch.domain.model.SuggestedUser
import com.denisvieira05.githubusersearch.domain.model.UserDetail
import kotlinx.coroutines.flow.Flow

interface UserRemoteDataSource {
    suspend fun getSuggestedUsers(): Flow<List<SuggestedUser>>

    suspend fun getUserDetail(userName: String): DataOrException<UserDetail, Exception>
}