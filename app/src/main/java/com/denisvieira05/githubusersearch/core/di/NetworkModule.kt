package com.denisvieira05.githubusersearch.core.di

import com.denisvieira05.githubusersearch.data.remote.user.UserAPI
import com.denisvieira05.githubusersearch.data.remote.config.retrofit.LoggedInterceptor
import com.denisvieira05.githubusersearch.data.remote.repository.RepositoryAPI
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

    private const val BASE_URL = "https://api.github.com/"

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
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideUserAPI(
        retrofit: Retrofit
    ): UserAPI {
        return retrofit.create(UserAPI::class.java)
    }

    @Provides
    fun provideRepositoryAPI(
        retrofit: Retrofit
    ): RepositoryAPI {
        return retrofit.create(RepositoryAPI::class.java)
    }
}