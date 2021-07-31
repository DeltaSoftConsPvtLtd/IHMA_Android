package com.deltasoft.ihma.base

import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.razorpay.PaymentData
import com.razorpay.PaymentResultWithDataListener

open class IlafBaseActivity : AppCompatActivity() {
    fun hideKeyboard() {
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        if (currentFocus != null) {
            inputMethodManager.hideSoftInputFromWindow(
                currentFocus!!.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }


}