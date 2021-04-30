package com.ilaftalkful.mobileonthego.bindingAdaptor

import android.view.KeyEvent
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView

import androidx.appcompat.widget.AppCompatSpinner
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField

@BindingAdapter("setWebViewClient")
fun setWebViewClient(view: WebView, client: WebViewClient?) {
    view.webViewClient = client
}

@BindingAdapter("loadUrl")
fun loadUrl(view: WebView, url: String?) {
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

