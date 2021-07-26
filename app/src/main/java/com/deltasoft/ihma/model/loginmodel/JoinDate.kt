package com.deltasoft.ihma.model.loginmodel

import androidx.databinding.BaseObservable
import com.google.gson.annotations.SerializedName

data  class JoinDate(val anme: String? = null) : BaseObservable() {

    @SerializedName("join_date")
    var join_date: String? = null



}






