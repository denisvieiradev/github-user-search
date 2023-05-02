package com.denisvieira05.githubusersearch.data.converters

import com.denisvieira05.githubusersearch.data.remote.user.responses.SuggestedUserResponse
import com.denisvieira05.githubusersearch.domain.model.SuggestedUser
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SuggestedUserConverter @Inject constructor() {

    fun mapResponseList(response: List<SuggestedUserResponse?>): List<SuggestedUser> {
        return response.filterNotNull().map {
            mapResponse(it)
        }
    }

    fun mapResponse(item: SuggestedUserResponse): SuggestedUser {
        return SuggestedUser(
            id = item.id,
            userName = item.userName,
            avatarUrl = item.avatarUrl
        )
    }
}