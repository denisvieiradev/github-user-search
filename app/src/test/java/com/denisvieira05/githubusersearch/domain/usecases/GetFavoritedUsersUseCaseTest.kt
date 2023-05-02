package com.denisvieira05.githubusersearch.domain.usecases

import com.denisvieira05.githubusersearch.data.local.favoriteduser.FavoritedUserLocalDataSource
import com.denisvieira05.githubusersearch.data.remote.user.UserRemoteDataSourceImpl
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class GetFavoritedUsersUseCaseTest {

    private lateinit var dataSource: FavoritedUserLocalDataSource
    private lateinit var useCase: GetFavoritedUsersUseCase

    @Before
    fun before() {
        dataSource = mockk(relaxed = true)
        useCase = GetFavoritedUsersUseCase(dataSource)
    }

    @Test
    fun `given usecase is called then should call getAllFavoritedUsers from datasource`() {
        runTest {
            useCase.invoke()

            coVerify(exactly = 1) { dataSource.getAllFavoritedUsers() }
        }
    }
}