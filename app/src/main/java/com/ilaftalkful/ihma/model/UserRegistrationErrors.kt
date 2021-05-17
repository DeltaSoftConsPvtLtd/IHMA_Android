package com.ilaftalkful.ihma.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.ilaftalkful.ihma.BR

data class UserRegistrationErrors(var userName : String?) : BaseObservable() {

    var firstnameError : String? = null
        @Bindable
        get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.firstnameError)
            }

        }

    var lastnameError : String? = null
        @Bindable
        get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.lastnameError)
            }

        }

    var registrationNoError : String? = null
        @Bindable
        get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.registrationNoError)
            }

        }

    var phoneNumberError : String? = null
        @Bindable
        get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.phoneNumberError)
            }

        }

    var userEmailError : String? = null
        @Bindable
        get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.userEmailError)
            }

        }
    var addressHomeError : String? = null
        @Bindable
        get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.addressHomeError)
            }

        }

    var addressClinicError : String? = null
        @Bindable
        get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.addressClinicError)
            }

        }

    var uiUpdate : Boolean? = null
        @Bindable
        get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.uiUpdate)
            }

        }

}