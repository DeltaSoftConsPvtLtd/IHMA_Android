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

class NotificationViewModel(application: Application) : AndroidViewModel(application) {
    var mutablenotificationlst = MutableLiveData<ArrayList<AlertModel>>()
    var notificationlist = arrayListOf<AlertModel>()
    internal var userLiveData: UserLiveUpdate? = null



    fun addNotificationApi() {
        userLiveData = UserLiveUpdate()

        val notificationService = UserService.create(getApplication<Application>(), false)
        val subscribe=
            notificationService?.doEventService()
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
                            userLiveData?.ViewingNotificationFailed()
                        }

                    } // this will tell you why your api doesnt work most of time

                }, { error ->

//                    userLiveData?.postError(ErrorData(100, null))
//                    error.printStackTrace()

                })

    }

    private fun processOnResponse(body: AlertResponse?) {

        for (item in body?.data?.get(0)?.details?.Notifications!!)
        {
            val event= AlertModel(item.id,item.name.toString())
            notificationlist.add(event)

        }

        if(notificationlist.size.equals(0)){
            userLiveData?.EmptyNotification()
        }else{
            mutablenotificationlst.postValue(notificationlist)
        }



    }



}