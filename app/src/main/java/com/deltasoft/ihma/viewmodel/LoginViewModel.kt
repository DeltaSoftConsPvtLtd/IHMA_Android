package com.deltasoft.ihma.view

import android.annotation.SuppressLint
import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.deltasoft.ihma.model.ErrorData
import com.deltasoft.ihma.model.SignInErrors
import com.deltasoft.ihma.model.UserDetails
import com.deltasoft.ihma.model.UserLiveUpdate
import com.deltasoft.ihma.model.otpModel.UserOTPResponse
import com.deltasoft.ihma.retrofit.RetrofitClient.Companion.pref
import com.deltasoft.ihma.retrofit.UserService
import com.deltasoft.ihma.utilities.IhmaValidator
import com.deltasoft.ihma.utilities.IlafSharedPreference
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class LoginViewModel(application: Application) : AndroidViewModel(application) {


    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    var contactCheckbox = false
    internal var userLiveData: UserLiveUpdate? = null
    var error: SignInErrors
    private var saveLogin: Boolean? = null
    var pref: IlafSharedPreference



    init {
        userLiveData = UserLiveUpdate()
        error = SignInErrors("")
        pref = IlafSharedPreference(application)


        saveLogin=IlafSharedPreference(application).getBooleanPrefValue(
            IlafSharedPreference.Constants.IS_LOGEDIN_USER)

        if (saveLogin == true) {
            password.postValue(pref!!.getStringPrefValue(IlafSharedPreference.Constants.PASSWORD))
            contactCheckbox=true
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun callLogin(error: SignInErrors) {
        userLiveData?.buttonClicked()
        val userDetails = UserDetails()
        userDetails.username = username.value?.trim()
        userDetails.password = password.value?.trim()



        if (IhmaValidator.isNullOrEmpty(error.userNameError)
            && IhmaValidator.isNullOrEmpty(error.userPasswordError)
        ) {
            error.uiUpdate = true
            tryLogin(userDetails, error)
        }
    }

    @SuppressLint("CheckResult")
    @RequiresApi(Build.VERSION_CODES.M)
    private fun tryLogin(userDetails: UserDetails,errorData: SignInErrors) {
        userLiveData?.processing()
        val loginService = UserService.create(getApplication<Application>(), false)
        val subscribe=
            loginService?.doSignIn(userDetails.username!!, userDetails.password!!)
                ?.observeOn(
                AndroidSchedulers.mainThread())
                ?.subscribeOn(
                    Schedulers.io()
                )
                ?.subscribe({
                    if (it.isSuccessful) {
                        if (it.code() == 200) {
                            errorData.uiUpdate = true

                            IlafSharedPreference(getApplication()).setStringPrefValue(IlafSharedPreference.Constants.USER_NAME, userDetails.username)
                            IlafSharedPreference(getApplication()).setStringPrefValue(IlafSharedPreference.Constants.PASSWORD, userDetails.password)

                            userLiveData?.userLoginSuccess()
                        } else {
                            errorData.uiUpdate = false
                            userLiveData?.userLoginFailed()
                        }
                    } else {
                        if (it.code() == 404) {
                            userLiveData?.userLoginFailed()
                        }
                        errorData.uiUpdate = false
                    } // this will tell you why your api doesnt work most of time

                }, { error ->
                    errorData.uiUpdate = false
                    userLiveData?.userLoginFailed()
                    userLiveData?.postError(ErrorData(100, null))
                    error.printStackTrace()

                })
    }



    var isValid: MediatorLiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSource(username) {
            value = it.isNotEmpty() && password.value?.isNotEmpty() ?: false
        }
        addSource(password) {
            value = it.isNotEmpty() && username.value?.isNotEmpty() ?: false
        }

    }


}

