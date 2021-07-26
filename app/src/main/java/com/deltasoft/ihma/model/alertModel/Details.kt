package com.deltasoft.ihma.model.alertModel

import com.google.gson.annotations.SerializedName

data class Details(val anme: String? = null) {

    @SerializedName("Notifications")
    var Notifications: List<Notifications>? = null

    @SerializedName("Events")
    var Events: List<Events>? = null

    @SerializedName("Flash_News")
    var Flash_News: List<FlashNews>? = null




}