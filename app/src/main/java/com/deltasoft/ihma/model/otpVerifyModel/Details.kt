package com.deltasoft.ihma.model.otpVerifyModel

import com.google.gson.annotations.SerializedName

data class Details(val anme: String? = null)  {

    @SerializedName("New Password")
    var newPassword: String? = null
    @SerializedName("Status")
    var Status: String? = null


}

