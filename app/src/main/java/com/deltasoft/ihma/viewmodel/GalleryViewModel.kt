package com.deltasoft.ihma.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.deltasoft.ihma.model.HomeModel

class GalleryViewModel(application: Application) : AndroidViewModel(application) {

    var lst = MutableLiveData<ArrayList<HomeModel>>()
    var newlist = arrayListOf<HomeModel>()

    fun add(blog: HomeModel){
        newlist.add(blog)
        lst.value=newlist
    }

}