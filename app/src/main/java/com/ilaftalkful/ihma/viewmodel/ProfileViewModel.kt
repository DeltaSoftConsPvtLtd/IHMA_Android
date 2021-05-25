package com.ilaftalkful.ihma.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ilaftalkful.ihma.model.UserLiveUpdate
import com.ilaftalkful.ihma.utilities.IlafSharedPreference

class ProfileViewModel (application: Application) : AndroidViewModel(application) {


    var ilafPreference: IlafSharedPreference
    var userName: MutableLiveData<String> = MutableLiveData<String>("")
    internal var userLiveData: UserLiveUpdate? = null

    init {
        ilafPreference = IlafSharedPreference(application)
        userLiveData = UserLiveUpdate()
        ilafPreference = IlafSharedPreference(application)
        userName.postValue(ilafPreference.getStringPrefValue(IlafSharedPreference.Constants.USER_NAME))
    }



}
