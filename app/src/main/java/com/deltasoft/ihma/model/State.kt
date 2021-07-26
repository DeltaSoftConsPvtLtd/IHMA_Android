package com.deltasoft.ihma.model

import com.google.gson.annotations.SerializedName

data class State(val anme: String? = null) {

    @SerializedName("districts")
    var districts: String? = null

    @SerializedName("state")
    var state: String? = null
}
