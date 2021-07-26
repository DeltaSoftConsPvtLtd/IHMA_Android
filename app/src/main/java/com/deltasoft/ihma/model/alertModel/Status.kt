package com.deltasoft.ihma.model.alertModel
import com.google.gson.annotations.SerializedName

data class Status(val anme: String? = null)  {

    @SerializedName("code")
    var code: Int? = null

    @SerializedName("error")
    var error: Boolean? = null

    @SerializedName("message")
    var message: String? = null

    @SerializedName("type")
    var type: String? = null

}