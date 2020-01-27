package com.railian.mobile.githubusersmvvm.di.application

import com.railian.mobile.githubusersmvvm.di.detailUser.DetailUserComponent
import com.railian.mobile.githubusersmvvm.di.detailUser.DetailUserModule
import com.railian.mobile.githubusersmvvm.di.usersList.UsersListComponent
import com.railian.mobile.githubusersmvvm.di.usersList.UsersListModule
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [GitHubApiModule::class]
)
@Singleton
interface AppComponent {
    fun getModule(module: UsersListModule): UsersListComponent
    fun getModule(module: DetailUserModule): DetailUserComponent
}