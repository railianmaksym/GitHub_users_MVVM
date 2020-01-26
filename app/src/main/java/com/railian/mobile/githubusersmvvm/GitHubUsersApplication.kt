package com.railian.mobile.githubusersmvvm

import android.app.Application
import com.railian.mobile.githubusersmvvm.di.application.AppComponent
import com.railian.mobile.githubusersmvvm.di.application.DaggerAppComponent
import com.railian.mobile.githubusersmvvm.di.application.GitHubApiModule

class GitHubUsersApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .gitHubApiModule(GitHubApiModule(this))
            .build()

    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}