package com.deltasoft.ihma.model.loginmodel

import androidx.databinding.BaseObservable
import com.google.gson.annotations.SerializedName

data  class User(val anme: String? = null) : BaseObservable() {

    @SerializedName("username")
    var username: String? = null



}






