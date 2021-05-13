package com.ilaftalkful.ihma.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ilaftalkful.ihma.utilities.IlafSharedPreference

class ProfileViewModel (application: Application) : AndroidViewModel(application) {
    var isGusetLogin: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    var ilafPreference: IlafSharedPreference

    init {
        ilafPreference = IlafSharedPreference(application)
        isGusetLogin.postValue(ilafPreference.getBooleanPrefValue(IlafSharedPreference.Constants.IS_GUEST_LOGIN))
    }



}
