package com.ilaftalkful.ihma.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ilaftalkful.ihma.model.NotificationModel

class MyCoursesViewModel(application: Application) : AndroidViewModel(application) {
    var lst = MutableLiveData<ArrayList<NotificationModel>>()
    var newlist = arrayListOf<NotificationModel>()

    fun add(notification: NotificationModel){
        newlist.add(notification)
        newlist.add(notification)
        newlist.add(notification)
        newlist.add(notification)
        newlist.add(notification)
        newlist.add(notification)
        lst.value=newlist
    }


}