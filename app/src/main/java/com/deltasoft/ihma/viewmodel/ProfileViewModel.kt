package com.deltasoft.ihma.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.deltasoft.ihma.model.UserLiveUpdate
import com.deltasoft.ihma.utilities.IlafSharedPreference

class ProfileViewModel (application: Application) : AndroidViewModel(application) {


    var ilafPreference: IlafSharedPreference
    var userName: MutableLiveData<String> = MutableLiveData<String>("")
    var phone: MutableLiveData<String> = MutableLiveData<String>("")
    var email: MutableLiveData<String> = MutableLiveData<String>("")
    var joingDate: MutableLiveData<String> = MutableLiveData<String>("")
    internal var userLiveData: UserLiveUpdate? = null

    init {
        ilafPreference = IlafSharedPreference(application)
        userLiveData = UserLiveUpdate()
        userName.postValue(ilafPreference.getStringPrefValue(IlafSharedPreference.Constants.USER_NAME))
        phone.postValue(ilafPreference.getStringPrefValue(IlafSharedPreference.Constants.PHONE))
        email.postValue(ilafPreference.getStringPrefValue(IlafSharedPreference.Constants.EMAIL_ID))
        joingDate.postValue(ilafPreference.getStringPrefValue(IlafSharedPreference.Constants.JOINING_DATE))
    }



}
