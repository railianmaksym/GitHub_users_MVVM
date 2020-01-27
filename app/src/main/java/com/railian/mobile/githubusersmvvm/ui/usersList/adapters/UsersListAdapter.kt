package com.railian.mobile.githubusersmvvm.ui.usersList.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.railian.mobile.githubusersmvvm.R
import com.railian.mobile.githubusersmvvm.data.pojo.GitHubUser
import com.railian.mobile.githubusersmvvm.ui.usersList.UsersListFragmentDirections
import com.railian.mobile.githubusersmvvm.ui.usersList.UsersListViewModel
import com.railian.mobile.githubusersmvvm.util.extensions.loadCircleImageFromUrl
import com.railian.mobile.githubusersmvvm.util.mvvm.NavigationCommand
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_users_list.*

class UsersListAdapter(
    private val viewModel: UsersListViewModel
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val ITEM_TYPE_USER = 1
        private const val ITEM_TYPE_LOADING = 2
    }

    var users: MutableList<GitHubUser?> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val holder: RecyclerView.ViewHolder
        val inflater = LayoutInflater.from(parent.context)
        holder = if (viewType == ITEM_TYPE_USER) {
            val view = inflater.inflate(R.layout.item_users_list, parent, false)
            UserViewHolder(view)
        } else {
            val view = inflater.inflate(R.layout.item_loading, parent, false)
            LoadingViewHolder(view)
        }
        return holder
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val user = users[position] ?: return
        if (holder is UserViewHolder) {
            with(holder) {
                username.text = user.login
                avatar.loadCircleImageFromUrl(user.avatarUrl)
                userLayout.setOnClickListener {
                    viewModel.navigationCommands.value =
                        NavigationCommand.To(
                            UsersListFragmentDirections.actionUsersListFragmentToUserDetailsFragment(
                                user.login
                            )
                        )
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (users[position] == null)
            ITEM_TYPE_LOADING
        else
            ITEM_TYPE_USER
    }

    fun showLoading() {
        users.add(null)
        notifyItemInserted(users.lastIndex)
    }

    fun addUsers(data: List<GitHubUser>) {
        val lastPosition = users.lastIndex
        users = users.filterNotNull() as MutableList<GitHubUser?>
        users.addAll(data)
        notifyItemChanged(lastPosition)
    }

    class UserViewHolder(override val containerView: View) : LayoutContainer,
        RecyclerView.ViewHolder(containerView)

    class LoadingViewHolder(override val containerView: View) : LayoutContainer,
        RecyclerView.ViewHolder(containerView)
}