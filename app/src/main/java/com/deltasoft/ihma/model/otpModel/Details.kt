package com.deltasoft.ihma.model.otpModel

import androidx.databinding.BaseObservable
import com.google.gson.annotations.SerializedName

open class Details(val anme: String? = null)  {

    @SerializedName("token")
    var token: String? = null
    @SerializedName("Status")
    var Status: String? = null


}

