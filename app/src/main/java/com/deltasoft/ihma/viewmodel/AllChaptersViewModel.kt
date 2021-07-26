package com.deltasoft.ihma.viewmodel

import android.app.Application
import android.graphics.Bitmap
import android.view.View
import android.webkit.*
import android.widget.ProgressBar
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData


class AllChaptersViewModel(application: Application) : AndroidViewModel(application) {

    val webViewUrl = MutableLiveData<String>().apply{ value =  "https://www.ihma.in/chapters" }

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


