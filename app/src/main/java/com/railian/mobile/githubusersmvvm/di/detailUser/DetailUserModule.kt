package com.railian.mobile.githubusersmvvm.di.detailUser

import com.railian.mobile.githubusersmvvm.data.network.GitHubApi
import com.railian.mobile.githubusersmvvm.ui.userDetails.UserDetailsRepository
import com.railian.mobile.githubusersmvvm.ui.userDetails.UserDetailsRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class DetailUserModule {

    @UserDetailsScope
    @Provides
    fun providesUserDetailsRepository(gitHubApi: GitHubApi): UserDetailsRepository {
        return UserDetailsRepositoryImpl(gitHubApi)
    }
}