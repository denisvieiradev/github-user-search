package com.denisvieira05.githubusersearch.data.converters

import com.denisvieira05.githubusersearch.data.remote.user.responses.UserDetailResponse
import com.denisvieira05.githubusersearch.domain.model.SuggestedUser
import com.denisvieira05.githubusersearch.domain.model.UserDetail
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDetailConverter @Inject constructor() {

    fun mapResponse(response: UserDetailResponse?): UserDetail? {
        return response?.let {
            UserDetail(
                id = it.id,
                userName = it.userName,
                avatarUrl = it.avatarUrl,
                completeName = it.completeName,
                htmlUrl = it.htmlUrl,
                followers = it.followers,
                following = it.following,
                repositories = it.repositories,
                blog = it.blog,
                twitterUsername = it.twitterUsername,
                bio = it.bio,
            )
        }
    }
}