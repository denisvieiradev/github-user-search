package com.denisvieira05.githubusersearch.data.remote.user

import com.denisvieira05.githubusersearch.domain.model.DataOrException
import com.denisvieira05.githubusersearch.domain.model.SuggestedUser
import com.denisvieira05.githubusersearch.domain.model.UserDetail

interface UserRemoteDataSource {
    suspend fun getSuggestedUsers(): DataOrException<List<SuggestedUser>, Exception>

    suspend fun getUserDetail(userName: String): DataOrException<UserDetail, Exception>
}