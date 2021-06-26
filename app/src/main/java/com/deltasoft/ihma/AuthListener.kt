package com.deltasoft.ihma

import androidx.lifecycle.LiveData

interface AuthListener {
    /**
     * Will be called when login button gets clicked
     */
    fun onStarted()
    fun onSuccess(loginResponse: LiveData<String>)
    fun onFailure(message:String)
}