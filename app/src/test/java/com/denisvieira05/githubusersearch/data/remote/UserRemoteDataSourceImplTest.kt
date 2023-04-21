package com.denisvieira05.githubusersearch.data.remote

import com.denisvieira05.githubusersearch.data.converters.SuggestedUserConverter
import com.denisvieira05.githubusersearch.data.converters.UserDetailConverter
import com.denisvieira05.githubusersearch.data.remote.repository.RepositoryRemoteDataSourceImpl
import com.denisvieira05.githubusersearch.data.remote.user.UserAPI
import com.denisvieira05.githubusersearch.data.remote.user.UserRemoteDataSourceImpl
import com.denisvieira05.githubusersearch.domain.model.UserDetail
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class UserRemoteDataSourceImplTest {

    private val fakeUserName = ""

    private val apiMock = mockk<UserAPI>(relaxed = true)
    private val suggestedUserConverterMock = mockk<SuggestedUserConverter>(relaxed = true)
    private val userDetailConverterMock = mockk<UserDetailConverter>(relaxed = true)

    private val repositoryRemoteDataSource =
        UserRemoteDataSourceImpl(apiMock, suggestedUserConverterMock, userDetailConverterMock)

    @Test
    fun `given getUserDetail from dataSource then should call getUserDetail from api`() = runTest {
        repositoryRemoteDataSource.getUserDetail(fakeUserName)

        coVerify { apiMock.getUserDetail(fakeUserName) }
    }
}