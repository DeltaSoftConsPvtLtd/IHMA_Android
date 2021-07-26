package com.deltasoft.ihma.bindingAdaptor

import android.annotation.SuppressLint
import android.os.Build
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.annotation.RequiresApi

import androidx.appcompat.widget.AppCompatSpinner
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import com.google.android.material.textfield.TextInputLayout


@BindingAdapter("setWebViewClient")
fun setWebViewClient(view: WebView, client: WebViewClient) {
    view.webViewClient = client
}

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
@SuppressLint("SetJavaScriptEnabled")
@BindingAdapter("loadUrl")
fun loadUrl(view: WebView, url: String) {

    view.getSettings().setDomStorageEnabled(true);
    view.getSettings().setJavaScriptEnabled(true);
    view.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
    view.getSettings().setPluginState(WebSettings.PluginState.ON);
    view.getSettings().setMediaPlaybackRequiresUserGesture(false);
    view.loadUrl(url)

}




@BindingAdapter("clicks")
    fun listenClicks(spinner: AppCompatSpinner, result: ObservableField<String>) {
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                result.set(parent?.getItemAtPosition(position) as String)
            }
        }

    }

@BindingAdapter("android:adapter")
fun setAutoCompleteAdapter(textView: AutoCompleteTextView, adapter: ArrayAdapter<*>) {
    textView.setAdapter(adapter)
}

    @BindingAdapter("app:errorText")
    fun setErrorText(view: TextInputLayout, errorMessage: String?) {
        if (errorMessage != null) {
            if (errorMessage.isEmpty()) {
                view.error = null
            } else
                view.error = errorMessage
        };

    }



