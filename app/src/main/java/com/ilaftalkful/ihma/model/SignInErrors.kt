package com.ilaftalkful.ihma.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.ilaftalkful.ihma.BR


data class SignInErrors(var userName : String?) : BaseObservable() {

    var userNameError : String? = ""
        @Bindable
        get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.userNameError)
            }

        }
    var userPasswordError : String? = ""
        @Bindable
        get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.userPasswordError)
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