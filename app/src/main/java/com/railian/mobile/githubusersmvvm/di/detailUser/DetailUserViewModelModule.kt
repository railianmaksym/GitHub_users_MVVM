package com.railian.mobile.githubusersmvvm.di.detailUser

import androidx.lifecycle.ViewModel
import com.railian.mobile.githubusersmvvm.di.application.ViewModelKey
import com.railian.mobile.githubusersmvvm.ui.userDetails.UserDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DetailUserViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(UserDetailsViewModel::class)
    abstract fun bindUserDetailsViewModel(serDetailsViewModel: UserDetailsViewModel): ViewModel
}