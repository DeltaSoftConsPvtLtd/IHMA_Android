package com.deltasoft.ihma.view

import android.annotation.SuppressLint
import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.deltasoft.ihma.model.ErrorData
import com.deltasoft.ihma.model.SignInErrors
import com.deltasoft.ihma.model.UserLiveUpdate
import com.deltasoft.ihma.model.loginmodel.UserDetails
import com.deltasoft.ihma.model.loginmodel.UserLoginResponse
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
    private var newPassword: String? = null
    var pref: IlafSharedPreference

    init {
        userLiveData = UserLiveUpdate()
        error = SignInErrors("")
        pref = IlafSharedPreference(application)

        saveLogin=IlafSharedPreference(application).getBooleanPrefValue(
            IlafSharedPreference.Constants.IS_LOGEDIN_USER)

        newPassword=IlafSharedPreference(application).getStringPrefValue(
            IlafSharedPreference.Constants.NEW_PASSWORD)

        if(saveLogin == true) {
            if(newPassword != null){
                username.postValue(pref.getStringPrefValue(IlafSharedPreference.Constants.USER_NAME))
                password.postValue(pref.getStringPrefValue(IlafSharedPreference.Constants.NEW_PASSWORD))
                contactCheckbox=true
            }else{
                username.postValue(pref.getStringPrefValue(IlafSharedPreference.Constants.USER_NAME))
                password.postValue(pref.getStringPrefValue(IlafSharedPreference.Constants.PASSWORD))
                contactCheckbox=true
            }
        }else{
            username.postValue("")
            password.postValue("")
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
    private fun tryLogin(userDetails: UserDetails, errorData: SignInErrors) {
        userLiveData?.processing()
        val loginService = UserService.create(getApplication<Application>(), false)
        val subscribe=
            loginService?.doSignIn(userDetails.username!!, userDetails.password!!)
                ?.observeOn(
                    AndroidSchedulers.mainThread()
                )
                ?.subscribeOn(
                    Schedulers.io()
                )
                ?.subscribe({
                    if (it.isSuccessful) {
                        if (it.code() == 200) {
                            errorData.uiUpdate = true

                            IlafSharedPreference(getApplication()).setStringPrefValue(
                                IlafSharedPreference.Constants.USER_NAME,
                                userDetails.username
                            )
                            IlafSharedPreference(getApplication()).setStringPrefValue(
                                IlafSharedPreference.Constants.PASSWORD,
                                userDetails.password
                            )
                            processResponse(it.body())

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

    private fun processResponse(body: UserLoginResponse?) {
        val username_value =body?.data?.get(0)?.user?.username
        val email_value =body?.data?.get(0)?.email?.email
        val phone_value =body?.data?.get(0)?.phone?.phone
        val joiningDate_value =body?.data?.get(0)?.join_date?.join_date
        val session_token=body?.data?.get(0)?.session_token
        IlafSharedPreference(getApplication()).setStringPrefValue(
            IlafSharedPreference.Constants.USER_NAME,
            username_value
        )
        IlafSharedPreference(getApplication()).setStringPrefValue(
            IlafSharedPreference.Constants.EMAIL_ID,
            email_value
        )
        IlafSharedPreference(getApplication()).setStringPrefValue(
            IlafSharedPreference.Constants.PHONE,
           phone_value
        )
        IlafSharedPreference(getApplication()).setStringPrefValue(
            IlafSharedPreference.Constants.JOINING_DATE,
            joiningDate_value
        )
        IlafSharedPreference(getApplication()).setStringPrefValue(
            IlafSharedPreference.Constants.SESSION_TOKEN,
            session_token
        )
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

