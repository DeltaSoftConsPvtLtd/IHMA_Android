package com.deltasoft.ihma.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.deltasoft.ihma.model.ErrorData
import com.deltasoft.ihma.model.SignInErrors
import com.deltasoft.ihma.model.UserLiveUpdate
import com.deltasoft.ihma.model.otpModel.UserOTPResponse
import com.deltasoft.ihma.retrofit.UserService
import com.deltasoft.ihma.utilities.IlafSharedPreference
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class EnterEmailViewModel(application: Application) : AndroidViewModel(application) {

    val email = MutableLiveData<String>()
    internal var userLiveData: UserLiveUpdate? = null
    var pref: IlafSharedPreference
   // var data: MutableLiveData<UserOTPResponse> = MutableLiveData<UserOTPResponse>()
    val otpResponse = MutableLiveData<UserOTPResponse>()



    init {
        userLiveData = UserLiveUpdate()
        pref = IlafSharedPreference(application)
    }

    fun callOTP() {
        val emailId = email.value?.trim()
        val otpService = UserService.create(getApplication<Application>(), false)
        val subscribe=
            otpService?.doOTPService(emailId.toString())
                ?.observeOn(
                    AndroidSchedulers.mainThread())
                ?.subscribeOn(
                    Schedulers.io()
                )
                ?.subscribe({
                    if (it.isSuccessful) {
                        if (it.code() == 200) {

                                userLiveData?.userOTPSuccess()
                                IlafSharedPreference(getApplication()).setStringPrefValue(
                                    IlafSharedPreference.Constants.EMAIL_ID,
                                    emailId
                                )
                                otpResponse.postValue(it.body())
                                processOnResponse(it.body())


                        } else {
                            userLiveData?.userOTPFailed()
                        }
                    } else {
                        if (it.code() == 404) {
                            userLiveData?.userOTPFailed()
                        }

                    } // this will tell you why your api doesnt work most of time

                }, { error ->

                    userLiveData?.userOTPFailed()
                    userLiveData?.postError(ErrorData(100, null))
                    error.printStackTrace()

                })


    }

    private fun processOnResponse(body: UserOTPResponse?) {
        pref.setStringPrefValue(IlafSharedPreference.Constants.TOKEN_KEY, body?.data?.details.token)
        //Log.d("Token",body?.details?.token.toString())


    }

}


