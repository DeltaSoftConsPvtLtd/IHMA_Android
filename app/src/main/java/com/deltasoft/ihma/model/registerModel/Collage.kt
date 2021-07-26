package com.deltasoft.ihma.model.registerModel

import com.google.gson.annotations.SerializedName

data class Collage(val anme: String? = null) {

    @SerializedName("name")
    var name: String? = null

    @SerializedName("id")
    var id: Int? = null
}