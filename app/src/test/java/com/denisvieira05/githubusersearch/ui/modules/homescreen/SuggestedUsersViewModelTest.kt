package com.denisvieira05.githubusersearch.ui.modules.homescreen

import app.cash.turbine.testIn
import com.denisvieira05.githubusersearch.CoroutineTestRule
import com.denisvieira05.githubusersearch.domain.model.SuggestedUser
import com.denisvieira05.githubusersearch.domain.model.UserDetail
import com.denisvieira05.githubusersearch.domain.usecases.GetSuggestedUsersUseCase
import com.denisvieira05.githubusersearch.ui.modules.suggestedusersscreen.SuggestedUsersScreenViewModel
import com.denisvieira05.githubusersearch.ui.modules.userdetailscreen.model.UserDetailUIState
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
class SuggestedUsersViewModelTest {

    private val getSuggestedUsersUseCase = mockk<GetSuggestedUsersUseCase>(relaxed = true)
    private val viewModel = SuggestedUsersScreenViewModel(getSuggestedUsersUseCase)

    private val scope = CoroutineScope(Dispatchers.Default)
    private val stateTurbine = viewModel.uiState.testIn(scope)

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Test
    fun `given fetchSuggestedUsers called then should update uiState with isLoading true`() =
        runTest {
            viewModel.fetchSuggestedUsers()

            val actual = viewModel.uiState.value

            assertThat(actual).isEqualTo(SuggestedUsersUIState.Loading)
        }

    @Test
    fun `given fetchSuggestedUsers when occurs any error then should update uiState with error state`() =
        runTest {
            coEvery { getSuggestedUsersUseCase.invoke() } throws Exception()

            viewModel.fetchSuggestedUsers()

            stateTurbine.skipItems(1)

            val actual = viewModel.uiState.value

            assertThat(actual).isEqualTo(SuggestedUsersUIState.Error)
        }

    @Test
    fun `given fetchSuggestedUsers called when not empty list then should update uiState with suggestedUsers filled list`() =
        runTest {
            coEvery { getSuggestedUsersUseCase.invoke() } returns flowOf(fakeData)

            viewModel.fetchSuggestedUsers()

            stateTurbine.skipItems(1)

            val actual = viewModel.uiState.value

            assertThat(actual).isEqualTo(SuggestedUsersUIState.Loaded(fakeData))
        }

    @Test
    fun `given fetchSuggestedUsers called when empty list then should update ui state with suggestedUsers correctly`() {
        val expected: List<SuggestedUser> = emptyList()
        coEvery { getSuggestedUsersUseCase.invoke() } returns flowOf(expected)

        runTest {
            viewModel.fetchSuggestedUsers()

            stateTurbine.skipItems(1)

            val actual = viewModel.uiState.value

            assertThat(actual).isEqualTo(SuggestedUsersUIState.Loaded(expected))
        }
    }

    private val fakeData = listOf(
        SuggestedUser(
            id = 12312,
            userName = "repositoryName",
            avatarUrl = "repository description",
        )
    )
}