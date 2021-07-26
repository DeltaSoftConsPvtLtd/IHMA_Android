package com.deltasoft.ihma.model.otpModel

import com.google.gson.annotations.SerializedName

data class Details(val anme: String? = null)  {

    @SerializedName("token")
    var token: String? = null
    @SerializedName("Status")
    var Status: String? = null


}

