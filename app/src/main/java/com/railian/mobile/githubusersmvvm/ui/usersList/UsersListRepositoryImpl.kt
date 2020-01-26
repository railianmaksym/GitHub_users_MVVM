package com.railian.mobile.githubusersmvvm.ui.usersList

import com.railian.mobile.githubusersmvvm.data.network.GitHubApi
import com.railian.mobile.githubusersmvvm.data.pojo.GitHubUser
import com.railian.mobile.githubusersmvvm.util.mvvm.Repository
import com.railian.mobile.githubusersmvvm.util.network.HttpResult
import javax.inject.Inject

class UsersListRepositoryImpl @Inject constructor(private val api: GitHubApi) : UsersListRepository,
    Repository() {
    override suspend fun loadUsers(
        lastLoadedUserId: Int,
        perPage: Int
    ): HttpResult<List<GitHubUser>> {
        return makeHttpRequest { api.loadUsers(lastLoadedUserId, perPage) }
    }
}