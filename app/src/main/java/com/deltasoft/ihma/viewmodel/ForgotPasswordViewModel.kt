package com.deltasoft.ihma.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class ForgotPasswordViewModel(application: Application) : AndroidViewModel(application) {

    val newpassword = MutableLiveData<String>()
    val confirmpassword = MutableLiveData<String>()


}