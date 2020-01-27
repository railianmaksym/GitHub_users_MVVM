package com.railian.mobile.githubusersmvvm.ui.userDetails


import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.railian.mobile.githubusersmvvm.GitHubUsersApplication
import com.railian.mobile.githubusersmvvm.R
import com.railian.mobile.githubusersmvvm.data.pojo.DetailGitHubUser
import com.railian.mobile.githubusersmvvm.di.application.DaggerViewModelFactory
import com.railian.mobile.githubusersmvvm.di.detailUser.DetailUserModule
import com.railian.mobile.githubusersmvvm.util.extensions.loadCircleImageFromUrl
import com.railian.mobile.githubusersmvvm.util.extensions.openUrl
import com.railian.mobile.githubusersmvvm.util.ui.ScreenState
import com.railian.mobile.githubusersmvvm.util.ui.SwipeRefreshFragment
import kotlinx.android.synthetic.main.appbar_title_only.*
import kotlinx.android.synthetic.main.fragment_user_details.*
import javax.inject.Inject

class UserDetailsFragment : SwipeRefreshFragment(R.layout.fragment_user_details) {

    private val args: UserDetailsFragmentArgs by navArgs()
    private lateinit var username: String

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory
    private lateinit var viewModel: UserDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GitHubUsersApplication.appComponent.getModule(DetailUserModule()).inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(UserDetailsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        username = args.username

        toolbar.title = username
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener { activity?.onBackPressed() }

        if (savedInstanceState == null) {
            viewModel.loadUserDetails(username)
        }

        viewModel.userData.observe(this,
            Observer<DetailGitHubUser> {
                showUserInfo(it)
            })
    }

    private fun showUserInfo(user: DetailGitHubUser) {

        avatarImage.loadCircleImageFromUrl(user.avatarUrl)
        usernameTXT.text = user.name ?: username
        accountUrl.text = user.htmlUrl

        reposCount.text = user.publicRepos.toString()
        gistsCount.text = user.publicGists.toString()
        followersCount.text = user.followers.toString()
        followingCount.text = user.following.toString()
    }

    override fun onStateChanged(screenState: ScreenState) {
        when (screenState) {
            ScreenState.CONTENT -> TODO()
            ScreenState.LOADING -> TODO()
            ScreenState.REFRESH -> TODO()
            ScreenState.ERROR -> TODO()
        }
    }

    override fun onRefresh() {
        viewModel.loadUserDetails(username, true)
    }
}
