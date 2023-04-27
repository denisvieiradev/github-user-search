package com.denisvieira05.githubusersearch.domain.usecases

import com.denisvieira05.githubusersearch.data.local.favoriteduser.FavoritedUserLocalDataSource
import com.denisvieira05.githubusersearch.domain.model.DataOrException
import com.denisvieira05.githubusersearch.domain.model.Repository
import com.denisvieira05.githubusersearch.domain.model.UserDetail
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
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
class CheckWasFavoritedUserUseCaseTest {

    private lateinit var dataSource: FavoritedUserLocalDataSource
    private lateinit var useCase: CheckWasFavoritedUserUseCase

    @Before
    fun before() {
        dataSource = mockk()
        useCase = CheckWasFavoritedUserUseCase(dataSource)
    }

    @Test
    fun `given usecase is called then should calls method from dataSource correctly`() {
        coEvery { dataSource.findByRemoteId(fakeRemoteId) }.returns(
            DataOrException(fakeData)
        )

        runTest {
            useCase(fakeRemoteId)

            coVerify(exactly = 1) { dataSource.findByRemoteId(fakeRemoteId) }
        }
    }

    @Test
    fun `given usecase is called when found user then should returns true`() {
        coEvery { dataSource.findByRemoteId(fakeRemoteId) }.returns(
            DataOrException(fakeData)
        )

        runTest {
            val result = useCase(fakeRemoteId)

            assertThat(result.data).isTrue()
        }
    }

    @Test
    fun `given usecase is called when not found user then should returns false`() {
        coEvery { dataSource.findByRemoteId(fakeRemoteId) }.returns(
            DataOrException(null)
        )

        runTest {
            val result = useCase(fakeRemoteId)

            assertThat(result.data).isFalse()
        }
    }

    private val fakeRemoteId = 123456L

    private val fakeData = UserDetail(
        id = 12312,
        completeName = "Name",
        userName = "repository description",
        avatarUrl = "",
        htmlUrl = "",
        followersCount = 123,
        followingCount = 123,
        repositoriesCount = 123,
        blog = "",
        bio = "3123",
        twitterUsername = "3123",
    )
}