package com.denisvieira05.githubusersearch.ui.modules.homescreen

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.testIn
import com.denisvieira05.githubusersearch.CoroutineTestRule
import com.denisvieira05.githubusersearch.domain.model.Repository
import com.denisvieira05.githubusersearch.domain.model.UserDetail
import com.denisvieira05.githubusersearch.domain.usecases.CheckWasFavoritedUserUseCase
import com.denisvieira05.githubusersearch.domain.usecases.GetRepositoriesUseCase
import com.denisvieira05.githubusersearch.domain.usecases.GetUserDetailUseCase
import com.denisvieira05.githubusersearch.domain.usecases.ToggleFavoritedUserUseCase
import com.denisvieira05.githubusersearch.ui.main.navigation.NavArguments.USERNAME_NAV_ARGUMENT
import com.denisvieira05.githubusersearch.ui.modules.userdetailscreen.UserDetailViewModel
import com.denisvieira05.githubusersearch.ui.modules.userdetailscreen.model.RepositoriesUIState
import com.denisvieira05.githubusersearch.ui.modules.userdetailscreen.model.UserDetailUIState
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UserDetailViewModelTest {

    private val getUserDetailUseCase = mockk<GetUserDetailUseCase>(relaxed = true)
    private val toggleFavoritedUserUseCase = mockk<ToggleFavoritedUserUseCase>(relaxed = true)
    private val getRepositoriesUseCase = mockk<GetRepositoriesUseCase>(relaxed = true)
    private val checkWasFavoritedUserUseCase = mockk<CheckWasFavoritedUserUseCase>(relaxed = true)
    private val savedStateHandle = mockk<SavedStateHandle>(relaxed = true)

    private val viewModel = UserDetailViewModel(
        savedStateHandle = savedStateHandle,
        getUserDetailUseCase = getUserDetailUseCase,
        getRepositoriesUseCase = getRepositoriesUseCase,
        toggleFavoritedUserUseCase = toggleFavoritedUserUseCase,
        checkWasFavoritedUserUseCase = checkWasFavoritedUserUseCase
    )

    private val scope = CoroutineScope(Dispatchers.Default)
//    private val stateTurbine = viewModel.uiState.testIn(scope)

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Before
    fun before() {
        every { savedStateHandle.get<String>(USERNAME_NAV_ARGUMENT) } returns fakeUserName
    }

    @Test
    fun `given fetchUserDetails called on fetchData call then should update uiState with isLoadingUser true`() =
        runTest {
            viewModel.fetchData()

            val actual = viewModel.userDetailUiState.value

            assertThat(actual).isEqualTo(UserDetailUIState.Loading)
        }

    @Test
    fun `given fetchUserRepositories called on fetchData call then should update uiState with isLoadingRepositories true`() =
        runTest {
            viewModel.fetchData()

            val actual = viewModel.repositoriesUiState.value

            assertThat(actual).isEqualTo(RepositoriesUIState.Loading)
        }

    // TODO tests to show error when fetchUserRepositories
//    @Test
//    fun `given fetchUserDetails called on fetchData call when occurs any error then should update uiState with isLoadingUser false`() =
//        runTest {
//            coEvery { getUserDetailUseCase.invoke(fakeUserName) } throws Exception()
//
//            viewModel.fetchData()
//
//            stateTurbine.skipItems(1)
//
//            val actual = viewModel.uiState.value
//
//            assertThat(actual.isLoadingUser).isEqualTo(false)
//        }

    // TODO tests to show error when fetchUserRepositories
//    @Test
//    fun `given fetchUserRepositories called on fetchData call when occurs any error then should update uiState with isLoadingRepositories false`() =
//        runTest {
//            coEvery { getUserDetailUseCase.invoke(fakeUserName) } throws Exception()
//
//            viewModel.fetchData()
//
//            stateTurbine.skipItems(1)
//
//            val actual = viewModel.uiState.value
//
//            assertThat(actual.isLoadingRepositories).isEqualTo(false)
//        }

    @Test
    fun `given fetchUserDetails called on fetchData call when not empty list then should update uiState with suggestedUsers filled list`() =
        runTest {
            coEvery { getUserDetailUseCase.invoke(fakeUserName) } returns flowOf(fakeUserDetail)

            viewModel.fetchData()

            val actual = viewModel.userDetailUiState.value

            assertThat(actual).isEqualTo(UserDetailUIState.Loaded(fakeUserDetail))
        }

    @Test
    fun `given fetchUserRepositories called on fetchData call when not empty list then should update uiState with suggestedUsers filled list`() =
        runTest {
            coEvery { getRepositoriesUseCase.invoke(fakeUserName) } returns flowOf(fakeRepositories)

            viewModel.fetchData()

//            stateTurbine.skipItems(1)

            val actual = viewModel.repositoriesUiState.value

            assertThat(actual).isEqualTo(RepositoriesUIState.Loaded(fakeRepositories))
        }

    @Test
    fun `given fetchUserRepositories called on fetchData when empty list then should update ui state with repositories correctly`() {
        val expected: List<Repository> = emptyList()
        coEvery { getRepositoriesUseCase.invoke(fakeUserName) } returns flowOf(expected)

        runTest {
            viewModel.fetchData()

//            stateTurbine.skipItems(1)

            val actual = viewModel.repositoriesUiState.value

            assertThat(actual).isEqualTo(RepositoriesUIState.Loaded(expected))
        }
    }

    private val fakeUserName = "userName"

    private val fakeUserDetail = UserDetail(
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

    private val fakeRepositories = listOf(
        Repository(
            id = 12312,
            name = "repositoryName",
            description = "description",
            htmlUrl = "hjtml_url",
            forks = 43,
            stars = 44,
            language = "Ruby"
        ),
        Repository(
            id = 12312,
            name = "repositoryName",
            description = "description",
            htmlUrl = "hjtml_url",
            forks = 43,
            stars = 44,
            language = "Ruby"
        ),
    )
}