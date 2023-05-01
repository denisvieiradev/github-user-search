package com.denisvieira05.githubusersearch.data.local

import com.denisvieira05.githubusersearch.data.converters.UserDetailConverter
import com.denisvieira05.githubusersearch.data.local.favoriteduser.FavoritedUserDAO
import com.denisvieira05.githubusersearch.data.local.favoriteduser.FavoritedUserLocalDataSourceImpl
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

    @Test
    fun `given getAllFavoritedUsers from dataSource then should call getAllFavoritedUsers from dao`() = runTest {
        dataSource.getAllFavoritedUsers()

        coVerify { daoMock.getAllFavoritedUsers() }
    }
}