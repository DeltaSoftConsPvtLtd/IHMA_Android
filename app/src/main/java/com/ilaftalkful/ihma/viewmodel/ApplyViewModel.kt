package com.ilaftalkful.ihma.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ilaftalkful.ihma.model.CoursesModel
import com.ilaftalkful.ihma.utilities.IlafSharedPreference

class ApplyViewModel(application: Application) : AndroidViewModel(application) {
    //    var isGusetLogin: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
//    lateinit var ilafPreference: IlafSharedPreference
//
//    init {
//        ilafPreference = IlafSharedPreference(application)
//        isGusetLogin.postValue(ilafPreference.getBooleanPrefValue(IlafSharedPreference.Constants.IS_GUEST_LOGIN))
//    }

    var lst = MutableLiveData<ArrayList<CoursesModel>>()
    var newlist = arrayListOf<CoursesModel>()

    fun add(blog: CoursesModel){
        newlist.add(blog)
        lst.value=newlist
    }

}