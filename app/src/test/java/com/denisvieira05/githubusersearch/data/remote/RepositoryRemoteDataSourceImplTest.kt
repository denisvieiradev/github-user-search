package com.denisvieira05.githubusersearch.data.remote

import com.denisvieira05.githubusersearch.data.converters.RepositoryConverter
import com.denisvieira05.githubusersearch.data.remote.repository.RepositoryAPI
import com.denisvieira05.githubusersearch.data.remote.repository.RepositoryRemoteDataSourceImpl
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@ExperimentalCoroutinesApi
//@RunWith(JUnit4::class)
class RepositoryRemoteDataSourceImplTest {

    private val fakeUserName = ""

    private val apiMock = mockk<RepositoryAPI>(relaxed = true)
    private val repositoryConverterMock = mockk<RepositoryConverter>(relaxed = true)

    private val repositoryRemoteDataSource =
        RepositoryRemoteDataSourceImpl(apiMock, repositoryConverterMock)

    // TODO FIX TEST to verify apiMock call
//    @Test
//    fun `given getRepositories then should call getRepositories from api`() = runTest {
//        repositoryRemoteDataSource.getRepositories(fakeUserName)
//
//        coVerify { apiMock.getRepositories(fakeUserName) }
//    }
}