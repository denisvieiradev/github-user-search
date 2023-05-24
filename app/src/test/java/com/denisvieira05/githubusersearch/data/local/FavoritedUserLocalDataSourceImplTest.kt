package com.denisvieira05.githubusersearch.data.local

import app.cash.turbine.test
import com.denisvieira05.githubusersearch.data.converters.UserDetailConverter
import com.denisvieira05.githubusersearch.data.local.favoriteduser.FavoritedUserDAO
import com.denisvieira05.githubusersearch.data.local.favoriteduser.FavoritedUserEntity
import com.denisvieira05.githubusersearch.data.local.favoriteduser.FavoritedUserLocalDataSourceImpl
import com.denisvieira05.githubusersearch.domain.model.UserDetail
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class FavoritedUserLocalDataSourceImplTest {

    private lateinit var daoMock: FavoritedUserDAO
    private lateinit var userDetailConverterMock : UserDetailConverter

    private lateinit var dataSource: FavoritedUserLocalDataSourceImpl

    @Before
    fun before() {
        daoMock = mockk(relaxed = true)
        userDetailConverterMock = mockk(relaxed = true)
        dataSource = FavoritedUserLocalDataSourceImpl(daoMock, userDetailConverterMock)
    }

    @Test
    fun `given getAllFavoritedUsers from dataSource then should call getAllFavoritedUsers from dao`() =
        runTest {
            dataSource.getAllFavoritedUsers()

            coVerify(exactly = 1) { daoMock.getAllFavoritedUsers() }
        }

    // TODO FIX TEST to verify userDetailConverterMock call
    @Test
    fun `given getAllFavoritedUsers from dataSource then should call mapFromEntityList from converter`() = runTest {
//        every { userDetailConverterMock.mapFromEntityList(fakeFavoriteUserEntityList) } returns fakeUserDetailList
//        coEvery { daoMock.getAllFavoritedUsers() } returns flowOf(fakeFavoriteUserEntityList)

        dataSource.getAllFavoritedUsers()

        coVerify (exactly = 1) { userDetailConverterMock.mapFromEntityList(any()) }
    }

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

    private val fakeFavoriteUserEntityList = listOf(
        fakeFavoritedUserEntity,
        fakeFavoritedUserEntity,
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

    private val fakeUserDetailList = listOf(
        fakeUserDetail,
        fakeUserDetail,
    )
}