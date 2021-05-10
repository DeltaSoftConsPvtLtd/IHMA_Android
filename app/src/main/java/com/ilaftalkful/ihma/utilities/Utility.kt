package com.ilaftalkful.ihma.utilities

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.view.View
import android.webkit.WebView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter



class Utility {
    companion object{
        fun checkInternetConnection(ctx: Context?): Boolean {
            val connectivityManager = ctx?.getSystemService(Context.CONNECTIVITY_SERVICE)
            return if (connectivityManager is ConnectivityManager) {
                val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
                networkInfo?.isConnected ?: false
            } else return false
        }
    }
}


