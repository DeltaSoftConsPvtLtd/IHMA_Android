package com.deltasoft.ihma.model

import com.google.gson.annotations.SerializedName

open class districtChapterModel(val anme: String? = null){

    @SerializedName("states")
    var states: List<State>? = null
        get() = field
        set(value) {
            field = value
        }

}

