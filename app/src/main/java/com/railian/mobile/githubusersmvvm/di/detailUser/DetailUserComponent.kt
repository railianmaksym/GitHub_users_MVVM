package com.railian.mobile.githubusersmvvm.di.detailUser

import com.railian.mobile.githubusersmvvm.ui.userDetails.UserDetailsFragment
import dagger.Subcomponent

@Subcomponent(modules = [DetailUserModule::class, DetailUserViewModelModule::class])
@UserDetailsScope
interface DetailUserComponent {
    fun inject(fragment: UserDetailsFragment)
}