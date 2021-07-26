package com.deltasoft.ihma.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.deltasoft.ihma.model.UserLiveUpdate
import com.deltasoft.ihma.model.alertModel.AlertModel
import com.deltasoft.ihma.model.alertModel.AlertResponse
import com.deltasoft.ihma.retrofit.UserService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FlasNewsViewModel(application: Application) : AndroidViewModel(application) {
    var mutableFlashNewslst = MutableLiveData<ArrayList<AlertModel>>()
    var flashnewslist = arrayListOf<AlertModel>()
    internal var userLiveData: UserLiveUpdate? = null



    fun addEventsApi() {
        userLiveData = UserLiveUpdate()

        val flashNewsService = UserService.create(getApplication<Application>(), false)
        val subscribe=
            flashNewsService?.doEventService()
                ?.observeOn(
                    AndroidSchedulers.mainThread())
                ?.subscribeOn(
                    Schedulers.io()
                )
                ?.subscribe({
                    if (it.isSuccessful) {
                        if (it.code() == 200) {
                            processOnResponse(it.body())

                        } else {
                            //
                        }
                    } else {
                        if (it.code() == 404) {
                            userLiveData?.ViewingFlashNewsFailed()
                        }

                    } // this will tell you why your api doesnt work most of time

                }, { error ->

//                    userLiveData?.postError(ErrorData(100, null))
//                    error.printStackTrace()

                })

    }

    private fun processOnResponse(body: AlertResponse?) {

        for (item in body?.data?.get(0)?.details?.Flash_News!!)
        {
            val event= AlertModel(item.id,item.name.toString())
            flashnewslist.add(event)

        }

        if(flashnewslist.size.equals(0)){
            userLiveData?.EmptyFlashNews()
        }else{
            mutableFlashNewslst.postValue(flashnewslist)
        }



    }

}