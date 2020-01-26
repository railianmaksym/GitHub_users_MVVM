package com.railian.mobile.githubusersmvvm.util.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.railian.mobile.githubusersmvvm.R

abstract class SwipeRefreshFragment(@LayoutRes private val layout: Int) : Fragment(layout),
    SwipeRefreshLayout.OnRefreshListener {
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(layout, container, false)
        swipeRefreshLayout = SwipeRefreshLayout(context!!).apply {
            addView(view)
            setOnRefreshListener(this@SwipeRefreshFragment)
            setColorSchemeColors(ContextCompat.getColor(context!!, R.color.black))
        }
        return swipeRefreshLayout
    }

    abstract fun onStateChanged(screenState: ScreenState)
}