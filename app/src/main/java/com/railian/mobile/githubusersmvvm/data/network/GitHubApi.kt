package com.railian.mobile.githubusersmvvm.data.network

import com.railian.mobile.githubusersmvvm.data.pojo.DetailGitHubUser
import com.railian.mobile.githubusersmvvm.data.pojo.GitHubUser
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {

    @GET("/users")
    suspend fun loadUsers(@Query("since") lastLoadedUserId: Int): List<GitHubUser>

    @GET("/users/{username}")
    suspend fun loadUserDetails(@Path("username") username: String): DetailGitHubUser
}