package com.railian.mobile.githubusersmvvm.ui.usersList

import com.railian.mobile.githubusersmvvm.data.pojo.GitHubUser
import com.railian.mobile.githubusersmvvm.util.network.HttpResult

interface UsersListRepository {
    suspend fun loadUsers(
        lastLoadedUserId: Int = 1,
        perPage: Int = 25
    ): HttpResult<List<GitHubUser>>
}