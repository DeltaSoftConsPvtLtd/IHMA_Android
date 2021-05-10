package com.ilaftalkful.ihma.model

import androidx.lifecycle.MutableLiveData

class UserLiveUpdate : MutableLiveData<UserData>() {

    var mData = UserData()

    fun postError(throwable: ErrorData) {
        postValue(mData.error(throwable))
    }

    fun userLoginSuccess() {
        postValue(mData.loginSuccess())
    }

    fun buttonClicked() {
        postValue(mData.buttonClicked())
    }

    fun responseSuccess() {
        postValue(mData.responseSuccess())
    }

    fun processing() {
        postValue(mData.processing())
    }

    fun userLoginFailed() {
        postValue(mData.userLoginFailed())
    }

}
