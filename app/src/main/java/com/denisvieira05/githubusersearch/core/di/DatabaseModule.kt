package com.denisvieira05.githubusersearch.core.di

import android.content.Context
import androidx.room.Room
import com.denisvieira05.githubusersearch.data.local.config.RoomDataBaseFactory
import com.denisvieira05.githubusersearch.data.local.favoriteduser.FavoritedUserDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val databaseName = "GithubUserSearchDataBase"

    @Provides
    fun favoritedUsersDao(
        @ApplicationContext context: Context,
    ): FavoritedUserDAO {
        return Room.databaseBuilder(
            context,
            RoomDataBaseFactory::class.java,
            databaseName
        ).build().favoritedUserDAO()
    }
}