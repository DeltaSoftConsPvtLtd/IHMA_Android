package com.deltasoft.ihma.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.deltasoft.ihma.model.CoursesModel
import com.deltasoft.ihma.model.HomeModel
import java.util.*
import kotlin.collections.ArrayList

class NewHomeViewModel(application: Application) : AndroidViewModel(application) {

    var lst = MutableLiveData<ArrayList<HomeModel>>()
    var newlist = arrayListOf<HomeModel>()

    var researchLst = MutableLiveData<ArrayList<HomeModel>>()
    var newResearchList = arrayListOf<HomeModel>()


    fun add(blog: HomeModel){
        newlist.add(blog)
        newlist.add(blog)
        newlist.add(blog)
        lst.value=newlist
    }

    fun addResearch(research: HomeModel){
        newResearchList.add(research)
        researchLst.value=newResearchList
    }

}