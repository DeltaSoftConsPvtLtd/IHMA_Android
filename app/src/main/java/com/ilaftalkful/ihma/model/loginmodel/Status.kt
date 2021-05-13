package com.ilaftalkful.ihma.model.loginmodel

import androidx.databinding.BaseObservable
import com.google.gson.annotations.SerializedName

data  class Status(val anme: String? = null) : BaseObservable() {

    @SerializedName("code")
    var code: Int? = null

    @SerializedName("error")
    var error: Boolean? = null

    @SerializedName("message")
    var message: String? = null

    @SerializedName("type")
    var type: String? = null

}






