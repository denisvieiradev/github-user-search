package com.denisvieira05.githubusersearch.data.converters

import com.denisvieira05.githubusersearch.data.remote.user.model.UserItemListResponse
import com.denisvieira05.githubusersearch.domain.model.SuggestedUser
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SuggestedUserConverter @Inject constructor() {
    fun mapResponse(response: List<UserItemListResponse?>): List<SuggestedUser> {
        return response.shuffled().mapNotNull {
            it?.let { item ->
                SuggestedUser(
                    id = item.id,
                    userName = item.userName,
                    avatarUrl = it.avatarUrl
                )
            }
        }
    }
}