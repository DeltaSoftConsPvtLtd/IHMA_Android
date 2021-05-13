package com.ilaftalkful.ihma.model.loginmodel

import androidx.databinding.BaseObservable
import com.google.gson.annotations.SerializedName
data  class UserLoginResponse(val anme: String? = null) : BaseObservable() {

    @SerializedName("data")
    var data: List<Data>? = null
    @SerializedName("ServiceAddonsList")
    var status: List<Status>? = null

}

