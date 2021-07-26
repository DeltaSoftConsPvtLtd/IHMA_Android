package com.deltasoft.ihma.model.registerModel

import com.google.gson.annotations.SerializedName

data class MemberFee(val anme: String? = null) {

    @SerializedName("name")
    var name: String? = null

    @SerializedName("id")
    var id: Int? = null

    @SerializedName("amount")
    var amount: String? = null

}