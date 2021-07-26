package com.deltasoft.ihma.model.loginmodel

import androidx.databinding.BaseObservable
import com.google.gson.annotations.SerializedName

data  class Data(val anme: String? = null) : BaseObservable() {

    @SerializedName("expires_at")
    var expires_at: String? = null


    @SerializedName("return_to_url")
    var return_to_url: String? = null

    @SerializedName("session_token")
    var session_token: String? = null

    @SerializedName("status")
    var status: String? = null


    @SerializedName("user")
    var user: User? = null

    @SerializedName("email")
    var email: Email? = null

    @SerializedName("phone")
    var phone: Phone? = null

    @SerializedName("join_date")
    var join_date: JoinDate? = null

}

