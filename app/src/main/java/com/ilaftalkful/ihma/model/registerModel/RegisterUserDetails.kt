package com.ilaftalkful.ihma.model.registerModel

import com.google.gson.annotations.SerializedName

open class RegisterUserDetails  {

    @SerializedName("username")
    var username : String? = null
        get() = field
        set(value) {
            field = value
        }
    @SerializedName("email")
    var email : String? = null
        get() = field
        set(value) {
            field = value
        }
    @SerializedName("password")
    var password : String? = null
        get() = field
        set(value) {
            field = value
        }
    @SerializedName("password2")
    var password2 : String? = null
        get() = field
        set(value) {
            field = value
        }


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
    @SerializedName("collage_name")
    var collage_name : Int? = null
        get() = field
        set(value) {
            field = value
        }

    @SerializedName("membership_fee")
    var membership_fee : Int? = null
        get() = field
        set(value) {
            field = value
        }
    @SerializedName("status")
    var status : String? = null
        get() = field
        set(value) {
            field = value
        }

    @SerializedName("profile_pic")
    var profile_pic : String? = null
        get() = field
        set(value) {
            field = value
        }

    @SerializedName("user_type")
    var user_type : String? = null
        get() = field
        set(value) {
            field = value
        }

    @SerializedName("chapter")
    var chapter : Int? = null
        get() = field
        set(value) {
            field = value
        }

    @SerializedName("id_no")
    var id_no : Int? = null
        get() = field
        set(value) {
            field = value
        }

    @SerializedName("blood_group")
    var blood_group : String? = null
        get() = field
        set(value) {
            field = value
        }

}