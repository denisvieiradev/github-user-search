package com.denisvieira05.githubusersearch.core.di

import android.content.Context
import com.denisvieira05.githubusersearch.core.resources.AndroidResourcesProvider
import com.denisvieira05.githubusersearch.core.resources.ResourcesProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ProvidersModule {

    @Provides
    fun resourcesProvider(@ApplicationContext context: Context): ResourcesProvider {
        return AndroidResourcesProvider(context.resources)
    }
}