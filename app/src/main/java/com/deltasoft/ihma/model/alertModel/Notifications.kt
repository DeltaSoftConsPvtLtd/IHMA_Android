package com.deltasoft.ihma.model.alertModel

import com.google.gson.annotations.SerializedName

data class Notifications(val anme: String? = null) {

    @SerializedName("name")
    var name: String? = null

    @SerializedName("id")
    var id: Int? = null
}