package com.railian.mobile.githubusersmvvm.ui.userDetails

import com.railian.mobile.githubusersmvvm.data.network.GitHubApi
import com.railian.mobile.githubusersmvvm.data.pojo.DetailGitHubUser
import com.railian.mobile.githubusersmvvm.util.mvvm.Repository
import com.railian.mobile.githubusersmvvm.util.network.HttpResult
import javax.inject.Inject

class UserDetailsRepositoryImpl @Inject constructor(private val api: GitHubApi) :
    UserDetailsRepository,
    Repository() {
    override suspend fun getUserDetails(username: String): HttpResult<DetailGitHubUser> {
        return makeHttpRequest { api.loadUserDetails(username) }
    }
}