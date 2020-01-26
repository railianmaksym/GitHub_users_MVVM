package com.railian.mobile.githubusersmvvm.di.application

import androidx.lifecycle.ViewModel
import com.railian.mobile.githubusersmvvm.ui.usersList.UsersListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(UsersListViewModel::class)
    internal abstract fun usersListViewModel(viewModel: UsersListViewModel): ViewModel
}