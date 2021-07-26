package com.deltasoft.ihma.model.loginmodel

import androidx.databinding.BaseObservable
import com.google.gson.annotations.SerializedName

data  class Email(val anme: String? = null) : BaseObservable() {

    @SerializedName("email")
    var email: String? = null



}






