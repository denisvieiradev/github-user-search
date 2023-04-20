package com.denisvieira05.githubusersearch.data.converters

import com.denisvieira05.githubusersearch.data.remote.repository.responses.RepositoryResponse
import com.denisvieira05.githubusersearch.domain.model.Repository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryConverter @Inject constructor() {

    fun mapResponse(response: List<RepositoryResponse>): List<Repository> {
        val responseMapped = response.map { item ->
            Repository(
                id = item.id,
                name = item.name,
                description = item.description,
                htmlUrl = item.htmlUrl,
                forks = item.forks,
                language = item.language,
                stars = item.stars,
            )
        }
        return responseMapped
    }

}