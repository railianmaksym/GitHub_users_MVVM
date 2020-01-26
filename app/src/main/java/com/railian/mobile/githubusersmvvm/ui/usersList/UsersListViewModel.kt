package com.railian.mobile.githubusersmvvm.ui.usersList

import androidx.lifecycle.MutableLiveData
import com.railian.mobile.githubusersmvvm.data.pojo.GitHubUser
import com.railian.mobile.githubusersmvvm.ui.usersList.adapters.UsersListAdapter
import com.railian.mobile.githubusersmvvm.util.mvvm.BaseViewModel
import com.railian.mobile.githubusersmvvm.util.mvvm.NavigationCommand
import com.railian.mobile.githubusersmvvm.util.network.HttpResult
import com.railian.mobile.githubusersmvvm.util.ui.ScreenState
import javax.inject.Inject

class UsersListViewModel @Inject constructor(
    private val repository: UsersListRepository
) : BaseViewModel() {

    private var lastLoadedUserId: Int = 1
    val usersListAdapter = UsersListAdapter(this)

    val screenState = MutableLiveData<ScreenState>()
    val navigationCommands = MutableLiveData<NavigationCommand>()

    fun loadUsers(isReload: Boolean = false) {
        screenState.value = if (isReload) ScreenState.REFRESH else ScreenState.LOADING

        launchCoroutine(
            {
                if (isReload) {
                    repository.loadUsers(perPage = usersListAdapter.itemCount)
                } else {
                    repository.loadUsers(lastLoadedUserId = lastLoadedUserId)
                }
            },
            {
                when (val result = it) {
                    is HttpResult.Success -> {
                        onSuccessLoadUsers(result.data, isReload)
                    }
                    is HttpResult.Error -> {
                        onErrorLoadUsers(result.exception)
                    }
                }
            }
        )
    }

    private fun onSuccessLoadUsers(data: List<GitHubUser>, isReload: Boolean) {
        if (isReload) {
            usersListAdapter.users = data as MutableList<GitHubUser>
        } else {
            usersListAdapter.addUsers(data)
            lastLoadedUserId = usersListAdapter.users.lastOrNull()?.id ?: 1
        }
        screenState.value = ScreenState.CONTENT
    }

    private fun onErrorLoadUsers(exception: Exception) {
        screenState.value = ScreenState.ERROR
    }

}