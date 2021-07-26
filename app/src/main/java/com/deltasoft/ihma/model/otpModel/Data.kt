package com.deltasoft.ihma.model.otpModel

import com.google.gson.annotations.SerializedName

data class Data(val anme: String? = null)  {

    @SerializedName("status")
    var status: String? = null

    @SerializedName("return_to_url")
    var return_to_url: Any? = null

    @SerializedName("details")
    var details: Details? = null

}


