package com.deltasoft.ihma.model.otpModel

import androidx.databinding.BaseObservable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class UserOTPResponse() : Serializable {

    @SerializedName("data")
    var `data`: List<Data>? = null
        get() = field
        set(value) {
            field = value
        }

    @SerializedName("status")
    var status: Status? = null
        get() = field
        set(value) {
            field = value
        }

    @SerializedName("details")
    var details: Details? = null
        get() = field
        set(value) {
            field = value
        }

}

