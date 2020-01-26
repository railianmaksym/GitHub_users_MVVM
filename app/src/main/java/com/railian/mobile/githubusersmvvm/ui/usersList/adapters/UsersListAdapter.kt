package com.railian.mobile.githubusersmvvm.ui.usersList.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.railian.mobile.githubusersmvvm.R
import com.railian.mobile.githubusersmvvm.data.pojo.GitHubUser
import com.railian.mobile.githubusersmvvm.util.extensions.loadCircleImageFromUrl
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_users_list.*

class UsersListAdapter : RecyclerView.Adapter<UsersListAdapter.UserViewHolder>() {

    var users: MutableList<GitHubUser> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_users_list, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        with(holder) {
            username.text = user.login
            avatar.loadCircleImageFromUrl(user.avatarUrl)
        }
    }

    fun addUsers(data: List<GitHubUser>) {
        val lastPosition = users.lastIndex
        users.addAll(data)
        notifyItemChanged(lastPosition)
    }

    class UserViewHolder(override val containerView: View) : LayoutContainer,
        RecyclerView.ViewHolder(containerView)
}