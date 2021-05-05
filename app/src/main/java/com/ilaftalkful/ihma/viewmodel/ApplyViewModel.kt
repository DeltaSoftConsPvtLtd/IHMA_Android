package com.ilaftalkful.ihma.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ilaftalkful.ihma.model.CoursesModel
import com.ilaftalkful.ihma.utilities.IlafSharedPreference

class ApplyViewModel(application: Application) : AndroidViewModel(application) {

    var lst = MutableLiveData<ArrayList<CoursesModel>>()
    var newlist = arrayListOf<CoursesModel>()

    fun add(blog: CoursesModel){
        newlist.add(blog)
        newlist.add(blog)
        newlist.add(blog)
        lst.value=newlist
    }

}