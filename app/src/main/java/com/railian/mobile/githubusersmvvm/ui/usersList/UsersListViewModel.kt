package com.railian.mobile.githubusersmvvm.ui.usersList

import androidx.lifecycle.ViewModel
import com.railian.mobile.githubusersmvvm.ui.usersList.adapters.UsersListAdapter

class UsersListViewModel(private val repository: UsersListRepository) : ViewModel() {

    private val lastLoadedUserId: Int = 1
    val usersListAdapter = UsersListAdapter()

    fun loadUsers() {

    }
}