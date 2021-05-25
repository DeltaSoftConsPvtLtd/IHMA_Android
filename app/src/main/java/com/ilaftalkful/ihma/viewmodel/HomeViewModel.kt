package com.ilaftalkful.ihma.viewmodel

import android.app.Application
import android.graphics.Bitmap
import android.view.View
import android.webkit.*
import android.widget.ProgressBar
import androidx.databinding.Bindable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ilaftalkful.ihma.utilities.IlafSharedPreference


class HomeViewModel(application: Application) : AndroidViewModel(application) {

    val webViewUrl = MutableLiveData<String>().apply{ value =  "http://elearnihma.in/course/index.php" }

    fun showWebView(webView: WebView, progressBar: ProgressBar) {

        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()

        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                progressBar.visibility = View.VISIBLE
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                progressBar.visibility = View.GONE
                super.onPageFinished(view, url)
            }

            override fun onReceivedError(
                view: WebView, request: WebResourceRequest,
                error: WebResourceError
            ) {
                super.onReceivedError(view, request, error)

            }
        }
    }
}


