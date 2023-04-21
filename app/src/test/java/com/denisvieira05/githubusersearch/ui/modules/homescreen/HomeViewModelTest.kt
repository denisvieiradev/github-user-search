package com.denisvieira05.githubusersearch.ui.modules.homescreen

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.denisvieira05.githubusersearch.MainDispatcherRule
import com.denisvieira05.githubusersearch.domain.model.DataOrException
import com.denisvieira05.githubusersearch.domain.model.SuggestedUser
import com.denisvieira05.githubusersearch.domain.usecases.GetSuggestedUsersUseCase
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private lateinit var getSuggestedUsersUseCase: GetSuggestedUsersUseCase
    private lateinit var viewModel: HomeViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun before() {
        getSuggestedUsersUseCase = mockk()

        viewModel = HomeViewModel(
            getSuggestedUsersUseCase = getSuggestedUsersUseCase
        )
    }

    @Test
    fun `given getSuggestedUsersUseCase called when empty list then should update ui state with suggestedUsers correctly`() {
        val expected: DataOrException<List<SuggestedUser>, Exception> =
            DataOrException(emptyList(), null)
        coEvery { getSuggestedUsersUseCase.invoke() }.returns(expected)

        runTest {
            viewModel.fetchSuggestedUsers()

            val job = async {
                assertThat(viewModel.uiState.value.suggestedUsers).isEqualTo(expected.data)
            }
            job.await()
        }
    }

    @Test
    fun `given getSuggestedUsersUseCase called when not empty list then should update ui state with suggestedUsers correctly`() {
        val expected: DataOrException<List<SuggestedUser>, Exception> = DataOrException(
            fakeData, null
        )
        coEvery { getSuggestedUsersUseCase.invoke() }.returns(expected)

        runTest {
            viewModel.fetchSuggestedUsers()

            val job = async {
                assertThat(
                    viewModel.uiState.value.suggestedUsers
                ).isEqualTo(
                    fakeData
                )
            }
            job.await()
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