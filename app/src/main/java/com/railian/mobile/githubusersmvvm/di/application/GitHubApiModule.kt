package com.railian.mobile.githubusersmvvm.di.application

import com.google.gson.GsonBuilder
import com.railian.mobile.githubusersmvvm.data.network.GitHubApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class GitHubApiModule {
    @Provides
    @Singleton
    internal fun providesGitHubApi(): GitHubApi {

        val gson = GsonBuilder()
            .create()

        val okHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(GitHubApi::class.java)
    }
}