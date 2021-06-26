package com.deltasoft.ihma.model

import com.google.gson.annotations.SerializedName


open class UserDetails {
    @SerializedName("username")
    var username: String? = null
        get() = field
        set(value) {
            field = value
        }
    @SerializedName("password")
    var password : String? = null
        get() = field
        set(value) {
            field = value
        }


}