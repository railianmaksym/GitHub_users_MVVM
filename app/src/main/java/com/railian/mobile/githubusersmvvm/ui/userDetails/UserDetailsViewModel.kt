package com.railian.mobile.githubusersmvvm.ui.userDetails

import androidx.lifecycle.MutableLiveData
import com.railian.mobile.githubusersmvvm.data.pojo.DetailGitHubUser
import com.railian.mobile.githubusersmvvm.util.mvvm.BaseViewModel
import com.railian.mobile.githubusersmvvm.util.network.HttpResult
import com.railian.mobile.githubusersmvvm.util.ui.ScreenState
import javax.inject.Inject

class UserDetailsViewModel @Inject constructor(
    private val repository: UserDetailsRepository
) : BaseViewModel() {

    val userData: MutableLiveData<DetailGitHubUser> = MutableLiveData()

    fun loadUserDetails(username: String, isRefreshing: Boolean = false) {
        screenState.value = if (isRefreshing) ScreenState.REFRESH else ScreenState.LOADING
        launchCoroutine(
            { repository.getUserDetails(username) },
            {
                when (it) {
                    is HttpResult.Success -> handleSuccessLoadDetailUser(it.data)
                    is HttpResult.Error -> handleErrorLoadDetailUser(it.exception)
                }
            }
        )
    }

    private fun handleSuccessLoadDetailUser(data: DetailGitHubUser) {
        userData.value = data
        screenState.value = ScreenState.CONTENT
    }

    private fun handleErrorLoadDetailUser(exception: Exception) {
        screenState.value = ScreenState.ERROR
    }
}