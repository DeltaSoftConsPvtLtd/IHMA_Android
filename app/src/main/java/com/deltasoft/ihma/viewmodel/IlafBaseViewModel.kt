package com.deltasoft.ihma.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.deltasoft.ihma.utilities.IlafSharedPreference


open class IlafBaseViewModel(application: Application) : AndroidViewModel(application) {

     var pref : IlafSharedPreference
    init {
    pref = IlafSharedPreference(application)

}
}
