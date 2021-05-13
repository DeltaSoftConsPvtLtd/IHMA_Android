package com.ilaftalkful.ihma.model.loginmodel

import androidx.databinding.BaseObservable
import com.google.gson.annotations.SerializedName

data  class User(val anme: String? = null) : BaseObservable() {

    @SerializedName("username")
    var username: String? = null


    @SerializedName("return_to_url")
    var return_to_url: String? = null

    @SerializedName("session_token")
    var session_token: String? = null

    @SerializedName("status")
    var status: String? = null


    @SerializedName("user")
    var user: User? = null

}






