package com.deltasoft.ihma.model.alertModel

import com.google.gson.annotations.SerializedName

data class FlashNews(val anme: String? = null) {

    @SerializedName("name")
    var name: String? = null

    @SerializedName("id")
    var id: Int? = null
}