package com.ilaftalkful.ihma.view

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ilaftalkful.ihma.model.ErrorData
import com.ilaftalkful.ihma.model.SignInErrors
import com.ilaftalkful.ihma.model.UserDetails
import com.ilaftalkful.ihma.model.UserLiveUpdate
import com.ilaftalkful.ihma.retrofit.UserService
import com.ilaftalkful.ihma.utilities.IhmaValidator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class LoginViewModel(application: Application) : AndroidViewModel(application) {


    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    internal var userLiveData: UserLiveUpdate? = null
    lateinit var error: SignInErrors
    val isUsernameEmpty = MutableLiveData<Boolean>(false)


    init {
        userLiveData = UserLiveUpdate()
        error = SignInErrors(null)

        if (username.value?.isNullOrEmpty() ?: false) {
            isUsernameEmpty.postValue(true)
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

    @RequiresApi(Build.VERSION_CODES.M)
    private fun tryLogin(userDetails: UserDetails,errorData: SignInErrors) {
        userLiveData?.processing()
        val loginService = UserService.create(getApplication<Application>(), false)
        val subscribe =
            loginService?.doSignIn(userDetails.username!!, userDetails.password!!)?.observeOn(
                AndroidSchedulers.mainThread()
            )
                ?.subscribeOn(
                    Schedulers.io()
                )
                ?.subscribe({
                    if (it.isSuccessful()) {
                        if (it.code() == 200) {
                            errorData.uiUpdate = true
                            userLiveData?.userLoginSuccess()
                        } else {
                            errorData.uiUpdate = false
                            userLiveData?.userLoginFailed()
                        }
                    } else {
                        if (it.code() == 400) {
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

