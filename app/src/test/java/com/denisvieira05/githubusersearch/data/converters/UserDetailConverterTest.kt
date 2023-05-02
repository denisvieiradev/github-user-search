package com.denisvieira05.githubusersearch.data.converters

import com.denisvieira05.githubusersearch.data.local.favoriteduser.FavoritedUserEntity
import com.denisvieira05.githubusersearch.data.remote.user.responses.UserDetailResponse
import com.denisvieira05.githubusersearch.domain.model.UserDetail
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class UserDetailConverterTest {

    private val userDetailConverter = UserDetailConverter()

    @Test
    fun `given mapResponse of converter is called then should convert to base model correctly`() {
        val result = userDetailConverter.mapFromResponse(fakeData)

        assertThat(result!!.id).isEqualTo(fakeData.id)
        assertThat(result.completeName).isEqualTo(fakeData.completeName)
        assertThat(result.userName).isEqualTo(fakeData.userName)
        assertThat(result.avatarUrl).isEqualTo(fakeData.avatarUrl)
        assertThat(result.htmlUrl).isEqualTo(fakeData.htmlUrl)
        assertThat(result.followersCount).isEqualTo(fakeData.followersCount)
        assertThat(result.followingCount).isEqualTo(fakeData.followingCount)
        assertThat(result.repositoriesCount).isEqualTo(fakeData.repositoriesCount)
        assertThat(result.blog).isEqualTo(fakeData.blog)
        assertThat(result.bio).isEqualTo(fakeData.bio)
        assertThat(result.twitterUsername).isEqualTo(fakeData.twitterUsername)
    }

    @Test
    fun `given mapFromEntity of converter is called then should convert to base model correctly`() {
        val result = userDetailConverter.mapFromEntity(fakeFavoritedUserEntity)

        assertThat(result!!.id).isEqualTo(fakeData.id)
        assertThat(result.completeName).isEqualTo(fakeData.completeName)
        assertThat(result.userName).isEqualTo(fakeData.userName)
        assertThat(result.avatarUrl).isEqualTo(fakeData.avatarUrl)
        assertThat(result.htmlUrl).isEqualTo(fakeData.htmlUrl)
        assertThat(result.followersCount).isEqualTo(fakeData.followersCount)
        assertThat(result.followingCount).isEqualTo(fakeData.followingCount)
        assertThat(result.repositoriesCount).isEqualTo(fakeData.repositoriesCount)
        assertThat(result.blog).isEqualTo(fakeData.blog)
        assertThat(result.bio).isEqualTo(fakeData.bio)
        assertThat(result.twitterUsername).isEqualTo(fakeData.twitterUsername)
    }

    @Test
    fun `given mapToEntity of converter is called then should convert to entity model correctly`() {
        val result = userDetailConverter.mapToEntity(fakeModel)

        assertThat(result.remoteId).isEqualTo(fakeData.id)
        assertThat(result.completeName).isEqualTo(fakeData.completeName)
        assertThat(result.userName).isEqualTo(fakeData.userName)
        assertThat(result.avatarUrl).isEqualTo(fakeData.avatarUrl)
        assertThat(result.htmlUrl).isEqualTo(fakeData.htmlUrl)
        assertThat(result.followers).isEqualTo(fakeData.followersCount)
        assertThat(result.following).isEqualTo(fakeData.followingCount)
        assertThat(result.repositoriesCount).isEqualTo(fakeData.repositoriesCount)
        assertThat(result.blog).isEqualTo(fakeData.blog)
        assertThat(result.bio).isEqualTo(fakeData.bio)
        assertThat(result.twitterUsername).isEqualTo(fakeData.twitterUsername)
    }

    private val fakeData = UserDetailResponse(
        id = 12312,
        completeName = "Name",
        userName = "repository description",
        avatarUrl = "avatar.com",
        htmlUrl = "htmlUrl",
        followersCount = 123,
        followingCount = 123,
        repositoriesCount = 123,
        blog = "blog.com",
        bio = "3123",
        twitterUsername = "3123",
    )

    private val fakeModel = UserDetail(
        id = 12312,
        completeName = "Name",
        userName = "repository description",
        avatarUrl = "avatar.com",
        htmlUrl = "htmlUrl",
        followersCount = 123,
        followingCount = 123,
        repositoriesCount = 123,
        blog = "blog.com",
        bio = "3123",
        twitterUsername = "3123",
    )

    private val fakeFavoritedUserEntity = FavoritedUserEntity(
        id = 1,
        remoteId = 12312,
        completeName = "Name",
        userName = "repository description",
        avatarUrl = "avatar.com",
        htmlUrl = "htmlUrl",
        followers = 123,
        following = 123,
        repositoriesCount = 123,
        blog = "blog.com",
        bio = "3123",
        twitterUsername = "3123",
    )

}