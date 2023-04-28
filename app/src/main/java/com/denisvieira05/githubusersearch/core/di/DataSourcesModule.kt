package com.denisvieira05.githubusersearch.core.di

import com.denisvieira05.githubusersearch.data.converters.RepositoryConverter
import com.denisvieira05.githubusersearch.data.converters.SuggestedUserConverter
import com.denisvieira05.githubusersearch.data.converters.UserDetailConverter
import com.denisvieira05.githubusersearch.data.local.favoriteduser.FavoritedUserDAO
import com.denisvieira05.githubusersearch.data.local.favoriteduser.FavoritedUserLocalDataSource
import com.denisvieira05.githubusersearch.data.local.favoriteduser.FavoritedUserLocalDataSourceImpl
import com.denisvieira05.githubusersearch.data.remote.repository.RepositoryAPI
import com.denisvieira05.githubusersearch.data.remote.repository.RepositoryRemoteDataSource
import com.denisvieira05.githubusersearch.data.remote.repository.RepositoryRemoteDataSourceImpl
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
        api: UserAPI,
        suggestedUserConverter: SuggestedUserConverter,
        userDetailConverter: UserDetailConverter
    ): UserRemoteDataSource {
        return UserRemoteDataSourceImpl(
            api = api,
            suggestedUserConverter = suggestedUserConverter,
            userDetailConverter = userDetailConverter
        )
    }

    @Provides
    fun provideRepositoryRemoteDataSource(
        api: RepositoryAPI,
        repositoryConverter: RepositoryConverter
    ): RepositoryRemoteDataSource {
        return RepositoryRemoteDataSourceImpl(
            api = api,
            repositoryConverter = repositoryConverter,
        )
    }

    @Provides
    fun provideFavoritedUsersLocalDataSource(
        dao: FavoritedUserDAO,
        userDetailConverter: UserDetailConverter
    ): FavoritedUserLocalDataSource {
        return FavoritedUserLocalDataSourceImpl(
            favoritedUserDAO = dao,
            userDetailConverter = userDetailConverter,
        )
    }
}