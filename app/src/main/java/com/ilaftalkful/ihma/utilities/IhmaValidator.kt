package com.ilaftalkful.ihma.utilities

import android.content.Context
import android.util.Patterns
import com.ilaftalkful.ihma.R

import java.util.regex.Pattern

class IhmaValidator(context: Context) {

    companion object {

        fun isValidUserPassword(password: String?,context:Context?): String? {
            var errorMSg: String? = null
            if (isNullOrEmpty(password)) {
                errorMSg = context?.getString(R.string.password_no_empty)
                return errorMSg
            }
//
            return errorMSg
        }



        fun isValidUserName(email: String,context:Context?): String? {
            var errorMSg: String? = null
            if (isNullOrEmpty(email)) {
                errorMSg = context?.getString(R.string.email_empty)
                return errorMSg
            }

            return errorMSg
        }

        fun isNullOrEmpty(str: String?): Boolean {
            if (str != null && !str.isEmpty())
                return false
            return true
        }


    }

}