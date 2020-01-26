package com.railian.mobile.githubusersmvvm.util.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.railian.mobile.githubusersmvvm.R

fun ImageView.loadImageFromUrl(url: String) {
    Glide.with(context)
        .load(url)
        .error(R.drawable.ic_github_logo)
        .placeholder(R.drawable.ic_github_logo)
        .into(this)
}

fun ImageView.loadCircleImageFromUrl(url: String) {
    Glide.with(context)
        .load(url)
        .transform(CircleCrop())
        .error(R.drawable.ic_github_logo)
        .placeholder(R.drawable.ic_github_logo)
        .into(this)
}