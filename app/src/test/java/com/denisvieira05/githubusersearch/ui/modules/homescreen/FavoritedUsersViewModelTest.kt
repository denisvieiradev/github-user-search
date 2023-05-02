package com.denisvieira05.githubusersearch.ui.modules.homescreen

import app.cash.turbine.testIn
import com.denisvieira05.githubusersearch.CoroutineTestRule
import com.denisvieira05.githubusersearch.domain.model.SuggestedUser
import com.denisvieira05.githubusersearch.domain.model.UserDetail
import com.denisvieira05.githubusersearch.domain.usecases.GetFavoritedUsersUseCase
import com.denisvieira05.githubusersearch.ui.modules.favoritedusers.FavoritedUsersScreenViewModel
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import java.lang.Exception

@OptIn(ExperimentalCoroutinesApi::class)
class FavoritedUsersViewModelTest {

    private val getFavoritedUsersUseCase = mockk<GetFavoritedUsersUseCase>(relaxed = true)
    private val viewModel = FavoritedUsersScreenViewModel(getFavoritedUsersUseCase)

    private val scope = CoroutineScope(Dispatchers.Default)
    private val stateTurbine = viewModel.uiState.testIn(scope)

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Test
    fun `given fetchFavoritedUsers called then should update uiState with isLoading true`() =
        runTest {
            viewModel.fetchFavoritedUsers()

            val actual = viewModel.uiState.value

            assertThat(actual.isLoading).isEqualTo(true)
        }

    @Test
    fun `given fetchFavoritedUsers when occurs any error then should update uiState with isLoading false`() =
        runTest {
            coEvery { getFavoritedUsersUseCase.invoke() } throws Exception()

            viewModel.fetchFavoritedUsers()

            stateTurbine.skipItems(1)

            val actual = viewModel.uiState.value

            assertThat(actual.isLoading).isEqualTo(false)
        }

    @Test
    fun `given fetchFavoritedUsers called when not empty list then should update uiState with favoritedUsers filled list`() =
        runTest {
            coEvery { getFavoritedUsersUseCase.invoke() } returns flowOf(fakeData)

            viewModel.fetchFavoritedUsers()

            stateTurbine.skipItems(1)

            val actual = viewModel.uiState.value

            assertThat(actual.favoritedUsers).isEqualTo(fakeData)
        }

    @Test
    fun `given fetchFavoritedUsers called when empty list then should update ui state with favoritedUsers correctly`() {
        val expected: List<UserDetail> = emptyList()
        coEvery { getFavoritedUsersUseCase.invoke() } returns flowOf(expected)

        runTest {
            viewModel.fetchFavoritedUsers()

            stateTurbine.skipItems(1)

            val actual = viewModel.uiState.value

            assertThat(actual.favoritedUsers).isEqualTo(expected)
        }
    }

    private val fakeData = listOf(
        UserDetail(
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
    )
}