package com.railian.mobile.githubusersmvvm.di.usersList

import com.railian.mobile.githubusersmvvm.ui.usersList.UsersListFragment
import dagger.Subcomponent

@Subcomponent(modules = [UsersListModule::class, UsersListViewModelModule::class])
@UsersListScope
interface UsersListComponent {
    fun inject(fragment: UsersListFragment)
}