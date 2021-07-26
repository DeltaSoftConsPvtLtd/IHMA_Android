package com.deltasoft.ihma.model.loginmodel

import androidx.databinding.BaseObservable
import com.google.gson.annotations.SerializedName

data  class Phone(val anme: String? = null) : BaseObservable() {

    @SerializedName("phone")
    var phone: String? = null



}






