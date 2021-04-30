package com.ilaftalkful.ihma.bindingAdaptor

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.BindingAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView


class BottomNavigationAdapter {
        @BindingAdapter("onNavigationItemSelected")
        fun setOnNavigationItemSelectedListener(
            view: BottomNavigationView, listener: BottomNavigationView.OnNavigationItemSelectedListener?
        ) {
            view.setOnNavigationItemSelectedListener(listener)
        }


    }
