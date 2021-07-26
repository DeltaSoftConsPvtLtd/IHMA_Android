package com.deltasoft.ihma.model.registerModel

import com.google.gson.annotations.SerializedName

data class Details(val anme: String? = null) {

    @SerializedName("MemberFee")
    var memberFee: List<MemberFee>? = null

    @SerializedName("Collage")
    var collage: List<Collage>? = null

    @SerializedName("Chapter")
    var chapter: List<Chapter>? = null

    @SerializedName("error")
    var error: String? = null


}