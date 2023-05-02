package com.denisvieira05.githubusersearch.domain.usecases

import com.denisvieira05.githubusersearch.data.local.favoriteduser.FavoritedUserLocalDataSource
import com.denisvieira05.githubusersearch.domain.model.UserDetail
import com.google.common.truth.Truth.assertThat
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class ToggleFavoritedUserUseCaseTest {

    private lateinit var dataSource: FavoritedUserLocalDataSource
    private lateinit var useCase: ToggleFavoritedUserUseCase

    @Before
    fun before() {
        dataSource = mockk(relaxed = true)
        useCase = ToggleFavoritedUserUseCase(dataSource)
    }

    @Test
    fun `given usecase is called when isFavorited true then should emit flow with REMOVED_FAVORITE action`() =
        runTest {
            useCase.invoke(fakeData, isFavorited = true).collect { result ->
                assertThat(result).isEqualTo(FavoritedActions.REMOVED_FAVORITE)
            }

//            verify(exactly = 1) { dataSource.removeFavoritedUser(remoteId = fakeData.id) }
        }

    @Test
    fun `given usecase is called when isFavorited false then should emit flow with SAVE_FAVORITE action`() =
        runTest {
            useCase.invoke(fakeData, isFavorited = false).collect { result ->
                assertThat(result).isEqualTo(FavoritedActions.SAVE_FAVORITE)
            }

//            coVerify(exactly = 1) { dataSource.saveAsFavoritedUser(fakeData) }
        }

    // TODO FIX TEST to verify datasource call
//    @Test
//    fun `given usecase is called when isFavorited true then should call removeFavoritedUser from datasource`() =
//        runTest {
//            useCase.invoke(fakeData, isFavorited = true)
//            verify { dataSource.removeFavoritedUser(remoteId = fakeData.id) }
//        }

    // TODO FIX TEST to verify datasource call
//    @Test
//    fun `given usecase is called when isFavorited false then should call saveAsFavoritedUser from datasource`() =
//        runTest {
//            useCase.invoke(fakeData, isFavorited = false)
//            verify(exactly = 1) { dataSource.saveAsFavoritedUser(fakeData) }
//        }

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