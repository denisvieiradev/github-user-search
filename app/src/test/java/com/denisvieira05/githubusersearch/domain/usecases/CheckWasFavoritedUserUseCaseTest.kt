package com.denisvieira05.githubusersearch.domain.usecases

import com.denisvieira05.githubusersearch.CoroutineTestRule
import com.denisvieira05.githubusersearch.data.local.favoriteduser.FavoritedUserLocalDataSource
import com.denisvieira05.githubusersearch.domain.model.UserDetail
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class CheckWasFavoritedUserUseCaseTest {

    private var dataSource = mockk<FavoritedUserLocalDataSource>()
    private lateinit var useCase: CheckWasFavoritedUserUseCase

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Before
    fun before() {
        useCase = CheckWasFavoritedUserUseCase(dataSource)
    }

    // TODO FIX TEST to verify datasource call
//    @Test
//    fun `given usecase is called then should calls method from dataSource correctly`() = runTest {
//        every { dataSource.findByRemoteId(fakeRemoteId) } returns flow { null }
//
//        useCase.invoke(fakeRemoteId)
//
//        verify { dataSource.findByRemoteId(fakeRemoteId) }
//    }

    // TODO FIX TEST to verify correct response emitted **FALSE_POSITIVE**
    @Test
    fun `given usecase is called when found user then should returns true`() = runTest {
        every { dataSource.findByRemoteId(fakeRemoteId) } returns flow { fakeData }

        useCase.invoke(fakeRemoteId).collect { result ->
            assertThat(result).isTrue()
        }
    }


    // TODO FIX TEST to verify correct response emitted **FALSE_POSITIVE**
    @Test
    fun `given usecase is called when not found user then should returns false`() = runTest {
        every { dataSource.findByRemoteId(fakeRemoteId) } returns flow { null }

        useCase.invoke(fakeRemoteId).collect { result ->
            assertThat(result).isFalse()
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