package com.denisvieira05.githubusersearch.core.di

import com.denisvieira05.githubusersearch.data.remote.user.UserAPI
import com.denisvieira05.githubusersearch.data.remote.retrofit.LoggedInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(LoggedInterceptor())
            .build()
    }

    @Provides
    fun provideApiRepository(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): UserAPI {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
            .create(UserAPI::class.java)
    }
}