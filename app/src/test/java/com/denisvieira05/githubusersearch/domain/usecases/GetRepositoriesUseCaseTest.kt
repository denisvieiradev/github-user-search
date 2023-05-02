package com.denisvieira05.githubusersearch.domain.usecases

import com.denisvieira05.githubusersearch.data.remote.repository.RepositoryRemoteDataSourceImpl
import com.denisvieira05.githubusersearch.domain.model.DataOrException
import com.denisvieira05.githubusersearch.domain.model.Repository
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
class GetRepositoriesUseCaseTest {

    private lateinit var remoteDataSource: RepositoryRemoteDataSourceImpl
    private lateinit var useCase: GetRepositoriesUseCase

    @Before
    fun before() {
        remoteDataSource = mockk(relaxed = true)
        useCase = GetRepositoriesUseCase(remoteDataSource)
    }

    @Test
    fun `given usecase is called then should be able to get repositories`() {
        runTest {
            useCase(fakeUserName)

            coVerify(exactly = 1) { remoteDataSource.getRepositories(fakeUserName) }
        }
    }

    private val fakeUserName = "userName"
}