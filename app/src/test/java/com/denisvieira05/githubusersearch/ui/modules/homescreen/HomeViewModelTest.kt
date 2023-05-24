package com.denisvieira05.githubusersearch.ui.modules.homescreen

import app.cash.turbine.testIn
import com.denisvieira05.githubusersearch.CoroutineTestRule
import com.denisvieira05.githubusersearch.domain.model.SuggestedUser
import com.denisvieira05.githubusersearch.domain.usecases.GetSuggestedUsersUseCase
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
class HomeViewModelTest {

    private val getSuggestedUsersUseCase = mockk<GetSuggestedUsersUseCase>(relaxed = true)
    private val viewModel: HomeViewModel = HomeViewModel(
        getSuggestedUsersUseCase = getSuggestedUsersUseCase
    )

    private val scope = CoroutineScope(Dispatchers.Default)
    private val stateTurbine = viewModel.uiState.testIn(scope)

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Test
    fun `given fetchSuggestedUsers called then should update uiState with Loading`() =
        runTest {
            viewModel.fetchSuggestedUsers()

            val actual = viewModel.uiState.value

            assertThat(actual).isEqualTo(SuggestedUsersUIState.Loading)
        }


    @Test
    fun `given fetchSuggestedUsers when occurs any error then should update uiState with Error state`() =
        runTest {
            coEvery { getSuggestedUsersUseCase.invoke() } throws Exception()

            viewModel.fetchSuggestedUsers()

            val actual = viewModel.uiState.value

            assertThat(actual).isEqualTo(SuggestedUsersUIState.Error)
        }

    @Test
    fun `given fetchSuggestedUsers called when not empty list then should update uiState with suggestedUsers filled list`() =
        runTest {
            coEvery { getSuggestedUsersUseCase.invoke() } returns flowOf(fakeData)

            viewModel.fetchSuggestedUsers()

            val actual = viewModel.uiState.value

            assertThat(actual).isEqualTo(SuggestedUsersUIState.Loaded(fakeData))
        }

    @Test
    fun `given getSuggestedUsersUseCase called when empty list then should update ui state with suggestedUsers correctly`() {
        val expected: List<SuggestedUser> = emptyList()
        coEvery { getSuggestedUsersUseCase.invoke() } returns flowOf(expected)

        runTest {
            viewModel.fetchSuggestedUsers()

            val actual = viewModel.uiState.value

            assertThat(actual).isEqualTo(SuggestedUsersUIState.Loaded(emptyList()))
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