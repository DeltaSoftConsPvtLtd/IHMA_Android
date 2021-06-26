package com.deltasoft.ihma.model

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

    fun userRegistrationSuccess() {
        postValue(mData.registrationSuccess())
    }

    fun sessionExpired() {
        postValue(mData.sessionExpired())
    }

    fun userRegisterFailed() {
        postValue(mData.registrationFailed())
    }

    fun userOTPSuccess() {
        postValue(mData.OTPSuccess())
    }

    fun userOTPFailed() {
        postValue(mData.OTPFailed())
    }
    fun userOTPVerificationSuccess() {
        postValue(mData.OTPVerificationSuccess())
    }

    fun userOTPVerificationFailed() {
        postValue(mData.OTPVerificationFailed())
    }

}
