package com.railian.mobile.githubusersmvvm.ui.usersList

import androidx.lifecycle.MutableLiveData
import com.railian.mobile.githubusersmvvm.data.pojo.GitHubUser
import com.railian.mobile.githubusersmvvm.ui.usersList.adapters.UsersListAdapter
import com.railian.mobile.githubusersmvvm.util.mvvm.BaseViewModel
import com.railian.mobile.githubusersmvvm.util.ui.ScreenState
import javax.inject.Inject

class UsersListViewModel @Inject constructor(
    private val repository: UsersListRepository
) : BaseViewModel() {

    private var lastLoadedUserId: Int = 1
    val usersListAdapter = UsersListAdapter()

    val screenState = MutableLiveData<ScreenState>()

    fun loadUsers(isReload: Boolean = false) {

        usersListAdapter.users = mutableListOf(
            GitHubUser(
                avatarUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/7/74/Kotlin-logo.svg/1200px-Kotlin-logo.svg.png",
                login = "user"
            ),
            GitHubUser(
                avatarUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/7/74/Kotlin-logo.svg/1200px-Kotlin-logo.svg.png",
                login = "user"
            ),
            GitHubUser(
                avatarUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/7/74/Kotlin-logo.svg/1200px-Kotlin-logo.svg.png",
                login = "user"
            )
        )

//        screenState.value = if (isReload) ScreenState.REFRESH else ScreenState.LOADING
//
//        launchCoroutine(
//            {
//                if (isReload) {
//                    repository.loadUsers(perPage = usersListAdapter.itemCount)
//                } else {
//                    repository.loadUsers(lastLoadedUserId = lastLoadedUserId)
//                }
//            },
//            {
//                when (val result = it) {
//                    is HttpResult.Success -> {
//                        onSuccessLoadUsers(result.data, isReload)
//                    }
//                    is HttpResult.Error -> {
//                        onErrorLoadUsers(result.exception)
//                    }
//                }
//            }
//        )
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