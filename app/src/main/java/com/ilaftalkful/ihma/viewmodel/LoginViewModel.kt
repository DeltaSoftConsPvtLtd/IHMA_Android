package com.ilaftalkful.ihma.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation.findNavController
import com.ilaftalkful.ihma.R

class LoginViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    /**
     * Two way bind-able fields
     */
    var username: String = ""
    var password: String = ""

    /**
     * To pass login result to activity
     */
    private val logInResult = MutableLiveData<String>()

    fun getLogInResult(): LiveData<String> = logInResult

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
            logInResult.value = "200"

        }




    }

}

