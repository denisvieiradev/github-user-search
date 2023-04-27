package com.denisvieira05.githubusersearch.domain.usecases

import com.denisvieira05.githubusersearch.data.remote.user.UserRemoteDataSourceImpl
import com.denisvieira05.githubusersearch.domain.model.DataOrException
import com.denisvieira05.githubusersearch.domain.model.UserDetail
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
class GetUserDetailUseCaseTest {

    private lateinit var remoteDataSource: UserRemoteDataSourceImpl
    private lateinit var useCase: GetUserDetailUseCase

    @Before
    fun before() {
        remoteDataSource = mockk()
        useCase = GetUserDetailUseCase(remoteDataSource)
    }

    @Test
    fun `given usecase is called then should be able to get repositories`() {
        coEvery { remoteDataSource.getUserDetail(fakeUserName) }.returns(
            DataOrException(fakeData)
        )

        runTest {
            useCase(fakeUserName)

            coVerify(exactly = 1) { remoteDataSource.getUserDetail(fakeUserName) }
        }
    }

    private val fakeUserName = "userName"

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