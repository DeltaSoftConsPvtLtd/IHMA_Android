package com.ilaftalkful.ihma.model.registerModel

import com.google.gson.annotations.SerializedName

open class RegisterUserDetails  {
    @SerializedName("first_name")
    var first_name : String? = null
        get() = field
        set(value) {
            field = value
        }
    @SerializedName("last_name")
    var last_name : String? = null
        get() = field
        set(value) {
            field = value
        }
    @SerializedName("registration_number")
    var registration_number : String? = null
        get() = field
        set(value) {
            field = value
        }
    @SerializedName("phone")
    var phone : String? = null
        get() = field
        set(value) {
            field = value
        }

    @SerializedName("address")
    var address : String? = null
        get() = field
        set(value) {
            field = value
        }

    @SerializedName("state")
    var state : String? = null
        get() = field
        set(value) {
            field = value
        }

    @SerializedName("districts")
    var districts : String? = null
        get() = field
        set(value) {
            field = value
        }
//    @SerializedName("collage_name")
//    var collage_name : String? = null
//        get() = field
//        set(value) {
//            field = value
//        }
//
//    @SerializedName("membership_fee")
//    var membership_fee : String? = null
//        get() = field
//        set(value) {
//            field = value
//        }
//    @SerializedName("status")
//    var status : String? = null
//        get() = field
//        set(value) {
//            field = value
//        }
//
//    @SerializedName("profile_pic")
//    var profile_pic : String? = null
//        get() = field
//        set(value) {
//            field = value
//        }
//
//    @SerializedName("user_type")
//    var user_type : String? = null
//        get() = field
//        set(value) {
//            field = value
//        }
//
    @SerializedName("chapter")
    var chapter : String? = null
        get() = field
        set(value) {
            field = value
        }

}