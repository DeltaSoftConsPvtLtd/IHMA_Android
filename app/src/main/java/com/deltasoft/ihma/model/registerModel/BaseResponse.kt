package com.deltasoft.ihma.model.registerModel

import com.google.gson.annotations.SerializedName

open class BaseResponse {

    @SerializedName("type")
    var type:String?=null
    @SerializedName("message")
    var message:String?=null
    @SerializedName("code")
    var code:Int?=null
    @SerializedName("error")
    var error:Boolean?=null



}
