package com.denisvieira05.githubusersearch.ui.modules.homescreen

import app.cash.turbine.test
import com.denisvieira05.githubusersearch.CoroutineTestRule
import com.denisvieira05.githubusersearch.domain.model.SuggestedUser
import com.denisvieira05.githubusersearch.domain.usecases.GetSuggestedUsersUseCase
import com.google.common.truth.Truth.assertThat
import io.mockk.mockk
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private val getSuggestedUsersUseCase = mockk<GetSuggestedUsersUseCase>(relaxed = true)
    private lateinit var viewModel: HomeViewModel

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Before
    fun before() {
        viewModel = HomeViewModel(
            getSuggestedUsersUseCase = getSuggestedUsersUseCase
        )
    }

    @Test
    fun `given fetchSuggestedUsers called then should update uiState with isLoading true`() =
        runTest {
            viewModel.fetchSuggestedUsers()

            viewModel.uiState.test {
                assertThat(awaitItem().isLoading).isEqualTo(true)
                cancelAndIgnoreRemainingEvents()
            }
        }

    // TODO test suggestedUsers state changed when returns data from useCase
//    @Test
//    fun `given getSuggestedUsersUseCase called when not empty list then should update uiState with suggestedUsers filled list`() =
//        runTest {
//            coEvery { getSuggestedUsersUseCase.invoke() } coAnswers { flow { fakeData }}
//
//            viewModel.fetchSuggestedUsers()
//
//            viewModel.uiState.test {
//                awaitItem()
//                assertThat(awaitItem()).isEqualTo(fakeData)
//                cancelAndIgnoreRemainingEvents()
//            }
//        }

    // TODO test suggestedUsers state changed when returns data from useCase
//    @Test
//    fun `given getSuggestedUsersUseCase called when empty list then should update ui state with suggestedUsers correctly`() {
//        val expected: List<SuggestedUser> = emptyList()
//        coEvery { getSuggestedUsersUseCase.invoke() }.returns(flow { expected })
//
//        runTest {
//            viewModel.fetchSuggestedUsers()
//
//            val job = async {
//                assertThat(viewModel.uiState.value.suggestedUsers!!.first()).isEqualTo(expected)
//            }
//            job.await()
//        }
//    }

    private val fakeData = listOf(
        SuggestedUser(
            id = 12312,
            userName = "repositoryName",
            avatarUrl = "repository description",
        )
    )
}