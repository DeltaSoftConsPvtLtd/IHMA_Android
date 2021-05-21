package com.ilaftalkful.ihma.viewmodel

import android.app.Application
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ilaftalkful.ihma.utilities.IlafSharedPreference


class HomeViewModel(application: Application) : AndroidViewModel(application){
    val webViewUrl  = "http://elearnihma.in/course/index.php"
    //val webViewUrl  = "https://www.google.com/"
    var isGusetLogin: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    var ilafPreference: IlafSharedPreference? = null

    init {
        ilafPreference = IlafSharedPreference(application)
        isGusetLogin.postValue(ilafPreference?.getBooleanPrefValue(IlafSharedPreference.Constants.IS_GUEST_LOGIN))
    }



    fun webClient(): WebViewClient? {
        return  Client()
    }



    private class Client : WebViewClient() {
        override fun onReceivedError(
            view: WebView, request: WebResourceRequest,
            error: WebResourceError
        ) {
            super.onReceivedError(view, request, error)
//        setHideProgress(true)
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
//        setHideProgress(true)
        }
    }
}


