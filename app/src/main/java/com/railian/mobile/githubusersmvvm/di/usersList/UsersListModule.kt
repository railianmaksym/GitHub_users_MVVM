package com.railian.mobile.githubusersmvvm.di.usersList

import com.railian.mobile.githubusersmvvm.data.network.GitHubApi
import com.railian.mobile.githubusersmvvm.ui.usersList.UsersListRepository
import com.railian.mobile.githubusersmvvm.ui.usersList.UsersListRepositoryImpl
import com.railian.mobile.githubusersmvvm.ui.usersList.UsersListViewModel
import dagger.Module
import dagger.Provides

@Module
class UsersListModule {

    @UsersListScope
    @Provides
    fun provideUsersListRepository(gitHubApi: GitHubApi): UsersListRepository{
        return UsersListRepositoryImpl(gitHubApi)
    }

    @UsersListScope
    @Provides
    fun provideUsersListViewModel(repository: UsersListRepository): UsersListViewModel {
        return UsersListViewModel(repository)
    }
}