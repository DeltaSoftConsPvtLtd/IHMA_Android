package com.deltasoft.ihma.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.deltasoft.ihma.model.ErrorData
import com.deltasoft.ihma.model.UserLiveUpdate
import com.deltasoft.ihma.model.otpModel.Details
import com.deltasoft.ihma.retrofit.UserService
import com.deltasoft.ihma.utilities.IlafSharedPreference
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class OTPViewModel(application: Application) : AndroidViewModel(application) {

    internal var userLiveData: UserLiveUpdate? = null
    val otpE1 = MutableLiveData<String>()
    val otpE2 = MutableLiveData<String>()
    val otpE3 = MutableLiveData<String>()
    val otpE4 = MutableLiveData<String>()
    val otpE5 = MutableLiveData<String>()
    val otpE6 = MutableLiveData<String>()
    var pref: IlafSharedPreference

    init {
        userLiveData = UserLiveUpdate()
        pref = IlafSharedPreference(application)
    }

    fun callOTPVerification() {

        val  detailsOTP = Details()
        tryOTPVerification(detailsOTP)
    }

    private fun tryOTPVerification(details: Details) {

        val OTPe1=otpE1.value?.trim()
        val OTPe2=otpE2.value?.trim()
        val OTPe3=otpE3.value?.trim()
        val OTPe4=otpE4.value?.trim()
        val OTPe5=otpE5.value?.trim()
        val OTPe6=otpE6.value?.trim()
        val combinedOTP=OTPe1+OTPe2+OTPe3+OTPe4+OTPe5+OTPe6

        val emailId =pref.getStringPrefValue(IlafSharedPreference.Constants.EMAIL_ID)
        val tokenId=pref.getStringPrefValue(IlafSharedPreference.Constants.TOKEN_KEY)
        val otp=combinedOTP
        val otpService = UserService.create(getApplication<Application>(), false)
        val subscribe=
            otpService?.doOTPVerificationService(emailId.toString(), tokenId.toString(),otp)
                ?.observeOn(
                    AndroidSchedulers.mainThread())
                ?.subscribeOn(
                    Schedulers.io()
                )
                    
                ?.subscribe({
                    if (it.isSuccessful) {
                        if (it.code() == 200) {
                            userLiveData?.userOTPVerificationSuccess()
                                IlafSharedPreference(getApplication()).setStringPrefValue(
                                IlafSharedPreference.Constants.NEW_PASSWORD,
                                it.body()?.data?.get(0)?.details?.newPassword)
                            Log.d("NewPassword", it.body()?.data?.get(0)?.details?.newPassword.toString())
                        } else {
                            userLiveData?.userOTPVerificationFailed()
                        }
                    } else {
                        if (it.code() == 404) {
                            userLiveData?.userOTPVerificationFailed()
                        }

                    } // this will tell you why your api doesnt work most of time

                }, { error ->

                    userLiveData?.userOTPVerificationFailed()
                    userLiveData?.postError(ErrorData(100, null))
                    error.printStackTrace()

                })

    }


}