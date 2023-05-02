package com.denisvieira05.githubusersearch.domain.usecases

import com.denisvieira05.githubusersearch.data.remote.user.UserRemoteDataSourceImpl
import com.denisvieira05.githubusersearch.domain.model.DataOrException
import com.denisvieira05.githubusersearch.domain.model.Repository
import com.denisvieira05.githubusersearch.domain.model.SuggestedUser
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class GetSuggestedUsersUseCaseTest {

    private lateinit var remoteDataSource: UserRemoteDataSourceImpl
    private lateinit var useCase: GetSuggestedUsersUseCase

    @Before
    fun before() {
        remoteDataSource = mockk(relaxed = true)
        useCase = GetSuggestedUsersUseCase(remoteDataSource)
    }

    @Test
    fun `given usecase is called then should call getSuggestedUsers on remoteDataSource`() {
        runTest {
            useCase()

            coVerify(exactly = 1) { remoteDataSource.getSuggestedUsers() }
        }
    }
}