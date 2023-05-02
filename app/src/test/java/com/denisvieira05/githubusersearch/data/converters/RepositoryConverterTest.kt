package com.denisvieira05.githubusersearch.data.converters

import com.denisvieira05.githubusersearch.data.remote.repository.responses.RepositoryResponse
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class RepositoryConverterTest {

    private val converter = RepositoryConverter()

    @Test
    fun `given mapResponse of converter is called then should convert to base model correctly`() {
        val baseModel = fakeResponse.first()
        val result = converter.mapResponse(baseModel)

        assertThat(result.id).isEqualTo(baseModel.id)
        assertThat(result.name).isEqualTo(baseModel.name)
        assertThat(result.description).isEqualTo(baseModel.description)
        assertThat(result.htmlUrl).isEqualTo(baseModel.htmlUrl)
        assertThat(result.forks).isEqualTo(baseModel.forks)
        assertThat(result.stars).isEqualTo(baseModel.stars)
        assertThat(result.language).isEqualTo(baseModel.language)
    }

    @Test
    fun `given mapResponseList of converter is called then should convert to a list with correct size`() {
        val result = converter.mapResponseList(fakeResponse)
        val sizeExpected = 2

        assertThat(sizeExpected).isEqualTo(result.size)

    }

    private val fakeResponse = listOf (
        RepositoryResponse(
            id = 12312,
            name = "repositoryName",
            description = "description",
            htmlUrl = "hjtml_url",
            forks = 43,
            stars = 44,
            language = "Ruby"
        ),
        RepositoryResponse(
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