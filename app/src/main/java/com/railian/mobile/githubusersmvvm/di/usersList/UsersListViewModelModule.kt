package com.railian.mobile.githubusersmvvm.di.usersList

import androidx.lifecycle.ViewModel
import com.railian.mobile.githubusersmvvm.di.application.ViewModelKey
import com.railian.mobile.githubusersmvvm.ui.usersList.UsersListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class UsersListViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(UsersListViewModel::class)
    abstract fun bindMyViewModel(usersListViewModel: UsersListViewModel): ViewModel
}