package com.railian.mobile.githubusersmvvm.ui.userDetails

import com.railian.mobile.githubusersmvvm.data.pojo.DetailGitHubUser
import com.railian.mobile.githubusersmvvm.util.network.HttpResult

interface UserDetailsRepository {
    suspend fun getUserDetails(username: String): HttpResult<DetailGitHubUser>
}