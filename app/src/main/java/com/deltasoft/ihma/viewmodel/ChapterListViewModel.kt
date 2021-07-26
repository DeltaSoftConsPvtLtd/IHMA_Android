package com.deltasoft.ihma.viewmodel

import android.app.Application
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.deltasoft.ihma.model.registerModel.ChaptersModel


class ChapterListViewModel(application: Application) : AndroidViewModel(application) {

    var lst = MutableLiveData<ArrayList<ChaptersModel>>()
    var newlist = arrayListOf<ChaptersModel>()

    private var stateValue = ""


    fun onSelectItem(
        parent: AdapterView<*>?,
        view: View?,
        pos: Int,
        id: Long
    ) {
        stateValue  = parent?.selectedItem as String
        Log.d("Value", stateValue as String)

        newlist.clear()

        if(stateValue.equals("Alappuzha")){
            val chapters= ChaptersModel("Alappuzha")
            val chapters1= ChaptersModel("Cherthala")
            val chapters2= ChaptersModel("Haripad")
            newlist.add(chapters)
            newlist.add(chapters1)
            newlist.add(chapters2)
            lst.postValue(newlist)

        }
        if(stateValue.equals("Ernakulam")){
            val chapters= ChaptersModel("Ernakulam City")
            val chapters1= ChaptersModel("Thripunithura")
            val chapters2= ChaptersModel("Kochi")
            val chapters3= ChaptersModel("DPM HMC Chapter")
            val chapters4= ChaptersModel("Paravur")
            val chapters5= ChaptersModel("Aluva")
            val chapters6= ChaptersModel("Perumbavoor")
            val chapters7= ChaptersModel("Muvatupuzha")
            newlist.add(chapters)
            newlist.add(chapters1)
            newlist.add(chapters2)
            newlist.add(chapters3)
            newlist.add(chapters4)
            newlist.add(chapters5)
            newlist.add(chapters6)
            newlist.add(chapters7)
            lst.postValue(newlist)

        }
        if(stateValue.equals("Idukki")){
            val chapters= ChaptersModel("Idukki")
            newlist.add(chapters)
            lst.postValue(newlist)

        }
        if(stateValue.equals("Kannur")){
            val chapters= ChaptersModel("Kannur")
            val chapters1= ChaptersModel("Iritty")
            val chapters2= ChaptersModel("Thalassery-Koothuparambu")
            val chapters3= ChaptersModel("Thaliparambu")
            newlist.add(chapters)
            newlist.add(chapters1)
            newlist.add(chapters2)
            newlist.add(chapters3)
            lst.postValue(newlist)

        }
        if(stateValue.equals("Kasaragod")){
            val chapters= ChaptersModel("Kasargod")
            val chapters1= ChaptersModel("Payannur-Thrikkaripur")
            newlist.add(chapters)
            newlist.add(chapters1)
            lst.postValue(newlist)

        }
        if(stateValue.equals("Kollam")){
            val chapters= ChaptersModel("Kollam")
            newlist.add(chapters)
            lst.postValue(newlist)

        }
        if(stateValue.equals("Kottayam")){
            val chapters= ChaptersModel("Kottayam City")
            val chapters1= ChaptersModel("ANSS College")
            val chapters2= ChaptersModel("Changanacherry")
            val chapters3= ChaptersModel("Pala")
            val chapters4= ChaptersModel("Vaikom")
            newlist.add(chapters)
            newlist.add(chapters1)
            newlist.add(chapters2)
            newlist.add(chapters3)
            newlist.add(chapters4)
            lst.postValue(newlist)

        }
        if(stateValue.equals("Kozhikode")){
            val chapters= ChaptersModel("Kozhikode City")
            val chapters1= ChaptersModel("Kozhikode South")
            val chapters2= ChaptersModel("Kozhikode East")
            val chapters3= ChaptersModel("GHMC")
            val chapters4= ChaptersModel("Vadakara")
            val chapters5= ChaptersModel("Perambra")
            val chapters6= ChaptersModel("Koilandy")
            newlist.add(chapters)
            newlist.add(chapters1)
            newlist.add(chapters2)
            newlist.add(chapters3)
            newlist.add(chapters4)
            newlist.add(chapters5)
            newlist.add(chapters6)
            lst.postValue(newlist)



        }
        if(stateValue.equals("Malappuram")){
            val chapters= ChaptersModel("Malappuram ")
            val chapters1= ChaptersModel("Perinthalmanna ")
            val chapters2= ChaptersModel("Kondotty ")
            val chapters3= ChaptersModel("Kuttippuram")
            val chapters4= ChaptersModel("Nilambur")
            val chapters5= ChaptersModel("Manjeri")
            val chapters6= ChaptersModel("Tirur")
            val chapters7= ChaptersModel("Tirurangadi")
            newlist.add(chapters)
            newlist.add(chapters1)
            newlist.add(chapters2)
            newlist.add(chapters3)
            newlist.add(chapters4)
            newlist.add(chapters5)
            newlist.add(chapters6)
            newlist.add(chapters7)
            lst.postValue(newlist)

        }
        if(stateValue.equals("Palakkad")){
            val chapters= ChaptersModel("Palakkad")
            val chapters1= ChaptersModel("Pattambi")
            newlist.add(chapters)
            newlist.add(chapters1)
            lst.postValue(newlist)

        }
        if(stateValue.equals("Pathanamthitta")){
            val chapters= ChaptersModel("Pathanamthitta")
            newlist.add(chapters)
            lst.postValue(newlist)

        }
        if(stateValue.equals("Thiruvananthapuram")){
            val chapters= ChaptersModel("Thiruvananthapuram")
            newlist.add(chapters)
            lst.postValue(newlist)

        }
        if(stateValue.equals("Thrissur")){
            val chapters= ChaptersModel("Thrissur City")
            val chapters1= ChaptersModel("Guruvayoor")
            newlist.add(chapters)
            newlist.add(chapters1)
            lst.postValue(newlist)

        }
        if(stateValue.equals("Wayanad")){
            val chapters= ChaptersModel("No Local Chapters found under this district")
            newlist.add(chapters)
            lst.postValue(newlist)

        }




    }

}


