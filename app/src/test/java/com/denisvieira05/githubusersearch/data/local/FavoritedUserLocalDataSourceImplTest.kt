package com.denisvieira05.githubusersearch.data.local

import com.denisvieira05.githubusersearch.data.converters.UserDetailConverter
import com.denisvieira05.githubusersearch.data.local.favoriteduser.FavoritedUserDAO
import com.denisvieira05.githubusersearch.data.local.favoriteduser.FavoritedUserEntity
import com.denisvieira05.githubusersearch.data.local.favoriteduser.FavoritedUserLocalDataSourceImpl
import com.denisvieira05.githubusersearch.data.remote.user.responses.UserDetailResponse
import com.denisvieira05.githubusersearch.domain.model.UserDetail
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class FavoritedUserLocalDataSourceImplTest {

    private val daoMock = mockk<FavoritedUserDAO>(relaxed = true)
    private val userDetailConverterMock = mockk<UserDetailConverter>(relaxed = true)

    private val dataSource =
        FavoritedUserLocalDataSourceImpl(daoMock, userDetailConverterMock)

    // TODO FIX TEST to verify daoMock call
//    @Test
//    fun `given getAllFavoritedUsers from dataSource then should call getAllFavoritedUsers from dao`() = runTest {
//        every { userDetailConverterMock.mapFromEntity(fakeFavoritedUserEntity) } returns fakeUserDetail
//
//        dataSource.getAllFavoritedUsers()
//
//        coVerify { daoMock.getAllFavoritedUsers() }
//    }

    // TODO FIX TEST to verify daoMock call
//    @Test
//    fun `given getAllFavoritedUsers from dataSource then should call mapFromEntity from converter`() = runTest {
//        every { userDetailConverterMock.mapFromEntity(fakeFavoritedUserEntity) } returns fakeUserDetail
//        dataSource.getAllFavoritedUsers()
//
//        coVerify { userDetailConverterMock.mapFromEntity(any()) }
//    }

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

    private val fakeUserDetail = UserDetail(
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