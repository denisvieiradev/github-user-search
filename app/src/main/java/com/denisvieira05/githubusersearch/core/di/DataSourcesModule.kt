package com.denisvieira05.githubusersearch.core.di

import com.denisvieira05.githubusersearch.data.converters.SuggestedUserConverter
import com.denisvieira05.githubusersearch.data.remote.user.UserAPI
import com.denisvieira05.githubusersearch.data.remote.user.UserRemoteDataSource
import com.denisvieira05.githubusersearch.data.remote.user.UserRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourcesModule {

    @Provides
    fun provideUserRemoteDataSource(
        apiRepository: UserAPI,
        suggestedUserConverter: SuggestedUserConverter,
    ): UserRemoteDataSource {
        return UserRemoteDataSourceImpl(
            api = apiRepository,
            suggestedUserConverter = suggestedUserConverter,
        )
    }
}