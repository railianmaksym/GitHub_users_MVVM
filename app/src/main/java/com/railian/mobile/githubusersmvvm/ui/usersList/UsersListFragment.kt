package com.railian.mobile.githubusersmvvm.ui.usersList


import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.railian.mobile.githubusersmvvm.GitHubUsersApplication
import com.railian.mobile.githubusersmvvm.R
import com.railian.mobile.githubusersmvvm.di.usersList.UsersListModule
import com.railian.mobile.githubusersmvvm.util.ui.LastItemListener
import com.railian.mobile.githubusersmvvm.util.ui.ScreenState
import com.railian.mobile.githubusersmvvm.util.ui.SwipeRefreshFragment
import kotlinx.android.synthetic.main.fragment_users_list.*
import javax.inject.Inject

class UsersListFragment : SwipeRefreshFragment(R.layout.fragment_users_list) {

    protected lateinit var viewModel: UsersListViewModel
        @Inject set
    private var isLoading: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GitHubUsersApplication.appComponent.getModule(UsersListModule()).inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        usersListRV.run {
            adapter = viewModel.usersListAdapter
            addOnScrollListener(object : LastItemListener() {
                override fun onLastItemVisible() {
                    if (!isLoading) {
                        viewModel.loadUsers()
                        isLoading = true
                    }
                }
            })
        }

        viewModel.screenState.observe(this, Observer<ScreenState> {
            onStateChanged(it)
        })

        if (viewModel.usersListAdapter.users.isEmpty()) {
            viewModel.loadUsers()
        }
    }

    override fun onStateChanged(screenState: ScreenState) {
        when (screenState) {
            ScreenState.CONTENT -> {
                isLoading = false
                swipeRefreshLayout.isRefreshing = false
            }
            ScreenState.LOADING -> {
            }
            ScreenState.REFRESH -> {
                swipeRefreshLayout.isRefreshing = true
            }
            ScreenState.ERROR -> {
            }
        }
    }

    override fun onRefresh() {
        viewModel.loadUsers(isReload = true)
    }
}
