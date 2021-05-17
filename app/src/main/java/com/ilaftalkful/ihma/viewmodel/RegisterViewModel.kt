package com.ilaftalkful.ihma.viewmodel

import android.app.Application
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.ilaftalkful.ihma.model.ErrorData
import com.ilaftalkful.ihma.model.UserLiveUpdate
import com.ilaftalkful.ihma.model.UserRegistrationErrors
import com.ilaftalkful.ihma.model.registerModel.RegisterUserDetails
import com.ilaftalkful.ihma.retrofit.UserService
import com.ilaftalkful.ihma.utilities.IhmaValidator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.crypto.Cipher

class RegisterViewModel(application: Application) : AndroidViewModel(application) {


    var error: UserRegistrationErrors
    internal var userLiveData: UserLiveUpdate? = null
    val firstName = MutableLiveData<String>()
    val lastName = MutableLiveData<String>()
    val registrationNo = MutableLiveData<String>()
    val mobile = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val addressHome = MutableLiveData<String>()
    val addressClinic = MutableLiveData<String>()


    init {
        userLiveData = UserLiveUpdate()
        error = UserRegistrationErrors("")

    }

   @RequiresApi(Build.VERSION_CODES.M)
  fun callRegistration(error: UserRegistrationErrors) {
//
        val userDetails = RegisterUserDetails()
        userDetails.first_name = firstName.value?.trim()
        userDetails.last_name = lastName.value?.trim()
        userDetails.registration_number = registrationNo.value?.trim()
        userDetails.phone = mobile.value?.trim()
        userDetails.address = addressHome.value?.trim()




       if (IhmaValidator.isNullOrEmpty(error.firstnameError)
           && IhmaValidator.isNullOrEmpty(error.lastnameError)
           && IhmaValidator.isNullOrEmpty(error.phoneNumberError)
           && IhmaValidator.isNullOrEmpty(error.userEmailError)
           && IhmaValidator.isNullOrEmpty(error.registrationNoError)
           && IhmaValidator.isNullOrEmpty(error.addressHomeError)
           && IhmaValidator.isNullOrEmpty(error.addressClinicError)
       ) {
            error.uiUpdate = true
            tryRegistration(userDetails, error)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun tryRegistration(userDetails: RegisterUserDetails, errorData: UserRegistrationErrors) {
        userLiveData?.processing()
        var loginService = UserService.create(getApplication<Application>(), false)
        val subscribe =
            loginService?.doRegisterIn()?.observeOn(
                AndroidSchedulers.mainThread()
            )
                ?.subscribeOn(
                    Schedulers.io()
                )
                ?.subscribe({
                    if (it.isSuccessful) {
                        if (it.isSuccessful) {
                            if (it.code() == 200) {
                                errorData.uiUpdate = true
                                userLiveData?.userRegistrationSuccess()
                            } else {
                                errorData.uiUpdate = false
                                userLiveData?.userRegisterFailed()
                            }
                        } else {
                            if (it.code() == 400) {
                                userLiveData?.userRegisterFailed()
                            }
                            errorData.uiUpdate = false
                        } // this will tell you why your api doesnt work most of time

                    }
                    // this will tell you why your api doesn't work most of time

                }, { error ->
                    errorData.uiUpdate = false
                    userLiveData?.postError(
                        ErrorData(100, null)
                    )
                    error.printStackTrace()

                })
  }


    var isValid: MediatorLiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSource(firstName) {
            value = it.isNotEmpty() && email.value?.isNotEmpty() ?: false
                    && lastName.value?.isNotEmpty() ?: false && registrationNo.value?.isNotEmpty() ?: false
                    && mobile.value?.isNotEmpty() ?: false
                    && addressHome.value?.isNotEmpty() ?: false
                    && addressClinic.value?.isNotEmpty() ?: false

        }
        addSource(email) {
            value = it.isNotEmpty() && firstName.value?.isNotEmpty() ?: false
                    && lastName.value?.isNotEmpty() ?: false && registrationNo.value?.isNotEmpty() ?: false
                    && mobile.value?.isNotEmpty() ?: false
                    && addressHome.value?.isNotEmpty() ?: false
                    && addressClinic.value?.isNotEmpty() ?: false
        }

        addSource(registrationNo) {
            value = it.isNotEmpty() && firstName.value?.isNotEmpty() ?: false
                    && email.value?.isNotEmpty() ?: false && lastName.value?.isNotEmpty() ?: false
                    && mobile.value?.isNotEmpty() ?: false
                    && addressHome.value?.isNotEmpty() ?: false
                    && addressClinic.value?.isNotEmpty() ?: false
        }
        addSource(mobile) {
            value = it.isNotEmpty() && firstName.value?.isNotEmpty() ?: false
                    && email.value?.isNotEmpty() ?: false && registrationNo.value?.isNotEmpty() ?: false
                    && lastName.value?.isNotEmpty() ?: false
                    && addressHome.value?.isNotEmpty() ?: false
                    && addressClinic.value?.isNotEmpty() ?: false
        }
        addSource(lastName) {
            value = it.isNotEmpty() && email.value?.isNotEmpty() ?: false
                    && firstName.value?.isNotEmpty() ?: false && registrationNo.value?.isNotEmpty() ?: false
                    && mobile.value?.isNotEmpty() ?: false
                    && addressHome.value?.isNotEmpty() ?: false
                    && addressClinic.value?.isNotEmpty() ?: false
        }
        addSource(addressHome) {
            value = it.isNotEmpty() && email.value?.isNotEmpty() ?: false
                    && firstName.value?.isNotEmpty() ?: false && registrationNo.value?.isNotEmpty() ?: false
                    && mobile.value?.isNotEmpty() ?: false
                    && lastName.value?.isNotEmpty() ?: false
                    && addressClinic.value?.isNotEmpty() ?: false
        }
        addSource(addressClinic) {
            value = it.isNotEmpty() && email.value?.isNotEmpty() ?: false
                    && firstName.value?.isNotEmpty() ?: false && registrationNo.value?.isNotEmpty() ?: false
                    && mobile.value?.isNotEmpty() ?: false
                    && lastName.value?.isNotEmpty() ?: false
                    && addressHome.value?.isNotEmpty() ?: false
        }



    }
//Method For Selecting Spinner Value

//    fun onSelectItem(
//        parent: AdapterView<*>?,
//        view: View?,
//        pos: Int,
//        id: Long
//    ) {
//        val value = parent?.getItemAtPosition(2)
//        Log.d("Value", value as String)
//        //pos                                 get selected item position
//        //view.getText()                      get lable of selected item
//        //parent.getAdapter().getItem(pos)    get item by pos
//        //parent.getAdapter().getCount()      get item count
//        //parent.getCount()                   get item count
//        //parent.getSelectedItem()            get selected item
//        //and other...
//    }
}