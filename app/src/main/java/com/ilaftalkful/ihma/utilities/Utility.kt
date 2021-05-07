package com.ilaftalkful.ihma.utilities

import android.view.View
import android.webkit.WebView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter

fun ProgressBar.show(){
    visibility = View.VISIBLE
}

fun ProgressBar.hide(){
    visibility = View.GONE
}