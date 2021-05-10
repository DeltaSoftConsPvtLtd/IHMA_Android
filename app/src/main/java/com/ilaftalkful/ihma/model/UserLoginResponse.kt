package com.ilaftalkful.ihma.model

import com.google.gson.annotations.SerializedName

class UserLoginResponse  {
    @SerializedName("expiry")
    var accessToken: String? = null
        get() = field
        set(value) {
            field = value
        }

    @SerializedName("token")
    var tokenType: String? = null
        get() = field
        set(value) {
            field = value
        }



}
