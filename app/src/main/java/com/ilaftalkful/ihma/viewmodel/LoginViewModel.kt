package com.ilaftalkful.ihma.view

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ilaftalkful.ihma.AuthListener
import com.ilaftalkful.ihma.onlineDatabase.Repository

class LoginViewModel : ViewModel() {

    var username: String = ""
    var password: String = ""

//    /**
//     * To pass login result to activity
//     */
    private val logInResult = MutableLiveData<String>()

    fun getLogInResult(): LiveData<String> = logInResult


    var authListener: AuthListener? = null

    /**
     * Called from activity on login button click
     */
    fun performValidation() {

        if (username.isBlank()) {
            logInResult.value = "Invalid username"
            return
        }
        else if(password.isBlank()){
            logInResult.value = "Invalid password"
            return
        }else{
           // IlafSharedPreference(requireContext()).setBooleanPrefValue(IlafSharedPreference.Constants.IS_GUEST_LOGIN,false)

            //val loginResponse = Repository().userLogin(username,password)
            // logInResult.value = loginResponse.toString()

            logInResult.value = "Login Success"



        }




    }


    fun onLogInClicked(view: View){
        authListener?.onStarted()
        if(username.isBlank()|| password.isBlank()){
            authListener?.onFailure("Invalid email or password")
            return
        }

        val loginResponse = Repository().userLogin(username,password)
        authListener?.onSuccess(loginResponse)
    }

}

