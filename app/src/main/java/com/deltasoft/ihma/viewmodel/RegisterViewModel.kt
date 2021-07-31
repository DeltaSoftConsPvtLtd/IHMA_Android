package com.deltasoft.ihma.viewmodel

import android.app.Application
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.deltasoft.ihma.R
import com.deltasoft.ihma.model.ErrorData
import com.deltasoft.ihma.model.State
import com.deltasoft.ihma.model.UserLiveUpdate
import com.deltasoft.ihma.model.UserRegistrationErrors
import com.deltasoft.ihma.model.registerModel.RegisterUserDetails
import com.deltasoft.ihma.model.registerModel.UserRegistrationResponse
import com.deltasoft.ihma.retrofit.UserService
import com.deltasoft.ihma.utilities.IhmaValidator
import com.deltasoft.ihma.utilities.Utility
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject


class RegisterViewModel(application: Application) : AndroidViewModel(application) {


    var error: UserRegistrationErrors
    internal var userLiveData: UserLiveUpdate? = null
    val userId = MutableLiveData<String>()
    val firstName = MutableLiveData<String>()
    val lastName = MutableLiveData<String>()
    val registrationNo = MutableLiveData<String>()
    val mobile = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val confirmpassword = MutableLiveData<String>()
    val addressHome = MutableLiveData<String>()
    val addressClinic = MutableLiveData<String>()
    private var stateValueSelected = ""
    private var districtValueSelected = ""
    var chapterValueSelected :Int = 0


    //Spinner
    var state_lst = MutableLiveData<ArrayList<String>>()
    var districtModelList= MutableLiveData<ArrayList<String>>()
    var chapter_mutableList = MutableLiveData<ArrayList<String>>()
    var stateModelList = arrayListOf<String>()
    var districtList = arrayListOf<String>()
    var chapterList = arrayListOf<String>()
    var chapterList_id = arrayListOf<Int>()







    init {
        userLiveData = UserLiveUpdate()
        error = UserRegistrationErrors("")
        bindJSONDataInFacilityList()

    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun callRegistration(error: UserRegistrationErrors) {

        val userDetails = RegisterUserDetails()

        userDetails.collage_name = 1
        userDetails.membership_fee = 1
        userDetails.status = "pending_approval"
        userDetails.user_type = "doctor"
        userDetails.blood_group = "A+ve"

        userDetails.id_no = Integer.parseInt(userId.value?.trim().toString())
        userDetails.first_name = firstName.value?.trim()
        userDetails.last_name = lastName.value?.trim()
        userDetails.registration_number = registrationNo.value?.trim()
        userDetails.phone = mobile.value?.trim()
        userDetails.address = addressHome.value?.trim()
        userDetails.username = username.value?.trim()
        userDetails.email = email.value?.trim()
        userDetails.password = password.value?.trim()
        userDetails.password2 = confirmpassword.value?.trim()
        userDetails.state = stateValueSelected.trim()
        userDetails.districts = districtValueSelected.trim()
        userDetails.chapter = chapterValueSelected


        if (IhmaValidator.isNullOrEmpty(error.userIdError)
            && IhmaValidator.isNullOrEmpty(error.userNameError)
            && IhmaValidator.isNullOrEmpty(error.passwordError)
            && IhmaValidator.isNullOrEmpty(error.phoneNumberError)
            && IhmaValidator.isNullOrEmpty(error.userEmailError)
            && IhmaValidator.isNullOrEmpty(error.registrationNoError)
        ) {
            error.uiUpdate = true
            tryRegistration(userDetails, error)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun tryRegistration(userDetails: RegisterUserDetails, errorData: UserRegistrationErrors) {
        userLiveData?.processing()
        val registerService = UserService.create(getApplication<Application>(), false)
        val subscribe =
            registerService?.doRegisterIn(userDetails)?.observeOn(
                AndroidSchedulers.mainThread()
            )
                ?.subscribeOn(
                    Schedulers.io()
                )
                ?.subscribe({
                    if (it.isSuccessful) {
                        if (it.code() == 200) {
                            errorData.uiUpdate = true
                            userLiveData?.userRegistrationSuccess()
                        }
                    } else if (it.code() == 404) {
                        errorData.uiUpdate = false

                        userLiveData?.userRegisterFailed()
//                        userLiveData?.postError(
//                            ErrorData(200, it.body()?.data?.get(0)?.details?.error)
//                        )
                    }// this will tell you why your api doesnt work most of time

                    // this will tell you why your api doesn't work most of time

                }, { error ->
                    errorData.uiUpdate = false
                    userLiveData?.postError(
                        ErrorData(100, null)
                    )
                    error.printStackTrace()

                })
    }



    var isValid: MediatorLiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSource(firstName) {
            value = it.isNotEmpty() && email.value?.isNotEmpty() ?: false
                    && lastName.value?.isNotEmpty() ?: false && registrationNo.value?.isNotEmpty() ?: false
                    && mobile.value?.isNotEmpty() ?: false
                    && addressHome.value?.isNotEmpty() ?: false
                    && addressClinic.value?.isNotEmpty() ?: false
                    && userId.value?.isNotEmpty() ?: false

        }
        addSource(email) {
            value = it.isNotEmpty() && firstName.value?.isNotEmpty() ?: false
                    && lastName.value?.isNotEmpty() ?: false && registrationNo.value?.isNotEmpty() ?: false
                    && mobile.value?.isNotEmpty() ?: false
                    && addressHome.value?.isNotEmpty() ?: false
                    && addressClinic.value?.isNotEmpty() ?: false
                    && userId.value?.isNotEmpty() ?: false
        }

        addSource(registrationNo) {
            value = it.isNotEmpty() && firstName.value?.isNotEmpty() ?: false
                    && email.value?.isNotEmpty() ?: false && lastName.value?.isNotEmpty() ?: false
                    && mobile.value?.isNotEmpty() ?: false
                    && addressHome.value?.isNotEmpty() ?: false
                    && addressClinic.value?.isNotEmpty() ?: false
                    && userId.value?.isNotEmpty() ?: false
        }
        addSource(mobile) {
            value = it.isNotEmpty() && firstName.value?.isNotEmpty() ?: false
                    && email.value?.isNotEmpty() ?: false && registrationNo.value?.isNotEmpty() ?: false
                    && lastName.value?.isNotEmpty() ?: false
                    && addressHome.value?.isNotEmpty() ?: false
                    && addressClinic.value?.isNotEmpty() ?: false
                    && userId.value?.isNotEmpty() ?: false
        }
        addSource(lastName) {
            value = it.isNotEmpty() && email.value?.isNotEmpty() ?: false
                    && firstName.value?.isNotEmpty() ?: false && registrationNo.value?.isNotEmpty() ?: false
                    && mobile.value?.isNotEmpty() ?: false
                    && addressHome.value?.isNotEmpty() ?: false
                    && addressClinic.value?.isNotEmpty() ?: false
                    && userId.value?.isNotEmpty() ?: false
        }
        addSource(addressHome) {
            value = it.isNotEmpty() && email.value?.isNotEmpty() ?: false
                    && firstName.value?.isNotEmpty() ?: false && registrationNo.value?.isNotEmpty() ?: false
                    && mobile.value?.isNotEmpty() ?: false
                    && lastName.value?.isNotEmpty() ?: false
                    && addressClinic.value?.isNotEmpty() ?: false
                    && userId.value?.isNotEmpty() ?: false
        }
        addSource(addressClinic) {
            value = it.isNotEmpty() && email.value?.isNotEmpty() ?: false
                    && firstName.value?.isNotEmpty() ?: false && registrationNo.value?.isNotEmpty() ?: false
                    && mobile.value?.isNotEmpty() ?: false
                    && lastName.value?.isNotEmpty() ?: false
                    && addressHome.value?.isNotEmpty() ?: false
                    && userId.value?.isNotEmpty() ?: false
        }
        addSource(userId) {
            value = it.isNotEmpty() && email.value?.isNotEmpty() ?: false
                    && firstName.value?.isNotEmpty() ?: false && registrationNo.value?.isNotEmpty() ?: false
                    && mobile.value?.isNotEmpty() ?: false
                    && lastName.value?.isNotEmpty() ?: false
                    && addressHome.value?.isNotEmpty() ?: false
                    && addressClinic.value?.isNotEmpty() ?: false
        }


    }

    private fun bindJSONDataInFacilityList() {
        stateModelList = ArrayList<String>()
        val statesJsonObject = JSONObject(
            Utility.loadJSONFromAsserts(
                getApplication(),
                "statesdistricts.json"
            ).toString()
        ) // Extension Function call here
        val jsonArray= statesJsonObject.getJSONArray("states")

        for (i in 0 until jsonArray.length()){
            val stateModel = State()
            val jsonObject = jsonArray.get(i) as JSONObject
            stateModel.state = jsonObject.getString("state")
            stateModelList.add(stateModel.state.toString())

        }
        state_lst.postValue(stateModelList)

    }

//Method For Selecting Spinner Value

    fun onSelectItem(
        parent: AdapterView<*>?,
        view: View?,
        pos: Int,
        id: Long
    ) {
        //stateValue = parent?.selectedItem as String
        districtList.clear()

        val statesJsonObject = JSONObject(
            Utility.loadJSONFromAsserts(getApplication(), "statesdistricts.json").toString()
        ) // Extension Function call here
        val jsonArray = statesJsonObject.getJSONArray("states")
        val jsonObject = jsonArray.getJSONObject(pos)
        val stateJsonValue = jsonObject.getString("state")
        val districtvalue = jsonObject.getJSONArray("districts")


        for (i in 0 until districtvalue.length()) {

            districtList.add(districtvalue.getString(i))
        }
        districtModelList.postValue(districtList)

        Log.d("state", stateJsonValue)
        Log.d("district", districtvalue.toString())

        if(parent?.getId() == R.id.state_spinner)
        {
            stateValueSelected = parent.selectedItem as String
            Log.d("State", stateValueSelected)
        }
        else if(parent?.getId() == R.id.district_spinner)
        {
            districtValueSelected = parent.selectedItem as String
            Log.d("District", districtValueSelected)

        }
        else{
            //Chapter id need to save
            val item_position: String = java.lang.String.valueOf(pos)

            val positonInt = Integer.valueOf(item_position)

            chapterValueSelected=chapterList_id.get(positonInt)

            Log.d("position", chapterValueSelected.toString())


        }

        //pos                                 get selected item position
        //view.getText()                      get lable of selected item
        //parent.getAdapter().getItem(pos)    get item by pos
        //parent.getAdapter().getCount()      get item count
        //parent.getCount()                   get item count
        //parent.getSelectedItem()            get selected item
        //and other...

    }



        //inorder to get chapter details from registration GET Api
        fun callRegistrationApi() {

            val registerService = UserService.create(getApplication<Application>(), false)
            val subscribe =
                registerService?.getRegisterDetails()?.observeOn(
                    AndroidSchedulers.mainThread()
                )
                    ?.subscribeOn(
                        Schedulers.io()
                    )
                    ?.subscribe({
                        if (it.isSuccessful) {
                            if (it.code() == 200) {
                                processOnResponse(it.body())


                            }
                        } else if (it.code() == 404) {


                        }// this will tell you why your api doesnt work most of time

                        // this will tell you why your api doesn't work most of time

                    }, { error ->
                        error.printStackTrace()
                    })
        }

    private fun processOnResponse(body: UserRegistrationResponse?) {
        val a =body?.data?.get(0)?.details?.chapter?.get(0)?.name
        Log.d("AAAAA", a.toString())


        for (item in body?.data?.get(0)?.details?.chapter!!)
        {
            chapterList.add(item.name.toString())
            chapterList_id.add(item.id!!)
        }

        chapter_mutableList.postValue(chapterList)

    }


}








