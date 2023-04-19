package com.denisvieira05.githubusersearch.data.remote.user

import com.denisvieira05.githubusersearch.domain.model.DataOrException
import com.denisvieira05.githubusersearch.domain.model.SuggestedUser

interface UserRemoteDataSource {
    suspend fun getSuggestedUsers(): DataOrException<List<SuggestedUser>, Exception>
}