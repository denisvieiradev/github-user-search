package com.denisvieira05.githubusersearch.data.converters

import com.denisvieira05.githubusersearch.data.remote.user.responses.SuggestedUserResponse
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class SuggestedUserConverterTest {

    private val converter = SuggestedUserConverter()

    @Test
    fun `given mapResponse of converter is called then should convert to base model correctly`() {
        val baseModel = fakeResponse.first()
        val result = converter.mapResponse(baseModel)

        assertThat(result.id).isEqualTo(baseModel.id)
        assertThat(result.userName).isEqualTo(baseModel.userName)
        assertThat(result.avatarUrl).isEqualTo(baseModel.avatarUrl)
    }

    @Test
    fun `given mapResponseList of converter is called then should convert to a list with correct size`() {
        val result = converter.mapResponseList(fakeResponse)
        val sizeExpected = 2

        assertThat(sizeExpected).isEqualTo(result.size)

    }

    private val fakeResponse = listOf (
        SuggestedUserResponse(
            id = 12312,
            userName = "userName",
            avatarUrl = "avatarUrl",
        ),
        SuggestedUserResponse(
            id = 12312,
            userName = "userName",
            avatarUrl = "avatarUrl",
        ),
    )

}