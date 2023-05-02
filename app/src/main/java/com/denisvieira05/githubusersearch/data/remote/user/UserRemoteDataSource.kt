package com.denisvieira05.githubusersearch.data.remote.user

import com.denisvieira05.githubusersearch.domain.model.SuggestedUser
import com.denisvieira05.githubusersearch.domain.model.UserDetail
import kotlinx.coroutines.flow.Flow

interface UserRemoteDataSource {
    fun getSuggestedUsers(): Flow<List<SuggestedUser>>

    fun getUserDetail(userName: String): Flow<UserDetail>
}