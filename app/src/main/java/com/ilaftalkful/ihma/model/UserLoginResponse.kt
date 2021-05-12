package com.ilaftalkful.ihma.model

import com.google.gson.annotations.SerializedName

open class UserLoginResponse  {
    @SerializedName("type")
    var type: String? = null
        get() = field
        set(value) {
            field = value
        }

    @SerializedName("message")
    var message: String? = null
        get() = field
        set(value) {
            field = value
        }
    @SerializedName("code")
    var code: Int? = null
        get() = field
        set(value) {
            field = value
        }
    @SerializedName("error")
    var error: Boolean? = null
        get() = field
        set(value) {
            field = value
        }

    @SerializedName("status")
    var status: String? = null
        get() = field
        set(value) {
            field = value
        }
    @SerializedName("return_to_url")
    var return_to_url: Any? = null
        get() = field
        set(value) {
            field = value
        }
    @SerializedName("username")
    var username: String? = null
        get() = field
        set(value) {
            field = value
        }

    @SerializedName("expires_at")
    var expires_at: String? = null
        get() = field
        set(value) {
            field = value
        }
    @SerializedName("session_token")
    var session_token: Long? = null
        get() = field
        set(value) {
            field = value
        }


}
