package com.denisvieira05.githubusersearch.data.converters

import com.denisvieira05.githubusersearch.data.remote.repository.responses.RepositoryResponse
import com.denisvieira05.githubusersearch.domain.model.Repository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryConverter @Inject constructor() {

    fun mapResponse(response: List<RepositoryResponse?>): List<Repository> {
        return response.mapNotNull {
            it?.let { item ->
                Repository(
                    id = item.id,
                    name = item.name,
                    description = item.description,
                    htmlUrl = it.htmlUrl,
                    forks = it.forks,
                    language = it.language,
                    stars = it.stars,
                )
            }
        }
    }

}