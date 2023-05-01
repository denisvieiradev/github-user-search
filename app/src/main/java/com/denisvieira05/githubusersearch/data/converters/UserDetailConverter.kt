package com.denisvieira05.githubusersearch.data.converters

import com.denisvieira05.githubusersearch.data.local.favoriteduser.FavoritedUserEntity
import com.denisvieira05.githubusersearch.data.remote.user.responses.UserDetailResponse
import com.denisvieira05.githubusersearch.domain.model.UserDetail
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDetailConverter @Inject constructor() {

    fun mapFromResponse(response: UserDetailResponse): UserDetail {
        return response.let {
            UserDetail(
                id = it.id,
                userName = it.userName,
                avatarUrl = it.avatarUrl,
                completeName = it.completeName,
                htmlUrl = it.htmlUrl,
                followersCount = it.followersCount,
                followingCount = it.followingCount,
                repositoriesCount = it.repositoriesCount,
                blog = it.blog,
                twitterUsername = it.twitterUsername,
                bio = it.bio,
            )
        }
    }


    fun mapFromEntity(entity: FavoritedUserEntity?): UserDetail? {
        return entity?.let {
            UserDetail(
                id = it.remoteId,
                userName = it.userName,
                avatarUrl = it.avatarUrl,
                completeName = it.completeName,
                htmlUrl = it.htmlUrl,
                followersCount = it.followers,
                followingCount = it.following,
                repositoriesCount = it.repositoriesCount,
                blog = it.blog,
                twitterUsername = it.twitterUsername,
                bio = it.bio,
            )
        }
    }

    fun mapToEntity(user: UserDetail): FavoritedUserEntity {
        return user.let {
            FavoritedUserEntity(
                remoteId = it.id,
                userName = it.userName,
                avatarUrl = it.avatarUrl,
                completeName = it.completeName,
                htmlUrl = it.htmlUrl,
                followers = it.followersCount,
                following = it.followingCount,
                repositoriesCount = it.repositoriesCount,
                blog = it.blog,
                twitterUsername = it.twitterUsername,
                bio = it.bio,
            )
        }
    }


}