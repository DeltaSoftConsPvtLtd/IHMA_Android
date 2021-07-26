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

class EventsViewModel(application: Application) : AndroidViewModel(application) {
    var mutableEventlst = MutableLiveData<ArrayList<AlertModel>>()
    var eventlist = arrayListOf<AlertModel>()
    internal var userLiveData: UserLiveUpdate? = null



    fun addEventsApi() {
        userLiveData = UserLiveUpdate()

        val eventService = UserService.create(getApplication<Application>(), false)
        val subscribe=
            eventService?.doEventService()
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
                            userLiveData?.ViewingEventsFailed()
                        }

                    } // this will tell you why your api doesnt work most of time

                }, { error ->

//                    userLiveData?.postError(ErrorData(100, null))
//                    error.printStackTrace()

                })

    }

    private fun processOnResponse(body: AlertResponse?) {

        for (item in body?.data?.get(0)?.details?.Events!!)
        {
            val event= AlertModel(item.id,item.name.toString())
            eventlist.add(event)

        }

        if(eventlist.size.equals(0)){
            userLiveData?.EmptyEvents()
        }else{
            mutableEventlst.postValue(eventlist)
        }



    }

}