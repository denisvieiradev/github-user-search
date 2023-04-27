package com.denisvieira05.githubusersearch.data.converters

import com.denisvieira05.githubusersearch.data.remote.user.responses.UserDetailResponse
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
    fun `given mapResponse of converter is called then should convert to entity correctly`() {
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

}