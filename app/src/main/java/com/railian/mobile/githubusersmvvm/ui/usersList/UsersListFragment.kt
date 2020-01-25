package com.railian.mobile.githubusersmvvm.ui.usersList


import android.os.Bundle
import android.view.View
import com.railian.mobile.githubusersmvvm.R
import com.railian.mobile.githubusersmvvm.util.ui.SwipeRefreshFragment
import kotlinx.android.synthetic.main.fragment_users_list.*

class UsersListFragment : SwipeRefreshFragment(R.layout.fragment_users_list) {

    protected lateinit var viewModel: UsersListViewModel
        @Inject set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //DI
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        usersListRV.adapter = viewModel.usersListAdapter
    }

    override fun onRefresh() {
        viewModel.loadUsers()
    }
}
