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

        fun isValidName(value: String,context: Context?): String? {
            var errorMSg: String? = null
            if (value.isEmpty()){
                errorMSg = context?.getString(R.string.name_empty)
            }
            if (!Pattern.matches("[a-zA-Z ]+", value)) {
                errorMSg = context?.getString(R.string.naem_alphabet_only)
            }
            return errorMSg
        }

        fun isValidMobile(phone: String,context:Context?): String? {
            var errorMSg: String? = null
            if (phone.isEmpty()){
                errorMSg = context?.getString(R.string.invalid_phone)
            }else if (phone.length <9) {
                if(!Pattern.matches("[0-9]+",phone)){
                    errorMSg = context?.getString(R.string.invalid_phone)
                }
            }else if (phone.length ==9) {
                if(!phone.contains(" ")){
                    errorMSg = context?.getString(R.string.invalid_phone)
                }
            }
            if (!Patterns.PHONE.matcher(phone).matches()) {
                errorMSg = context?.getString(R.string.invalid_phone)
            }
            return errorMSg
        }

        fun isValidEmails(email: String,context:Context?): String? {
            var errorMSg: String? = null
            if (isNullOrEmpty(email)) {
                errorMSg = context?.getString(R.string.email_empty)
                return errorMSg
            }
            if (!isValidEmail(email)) {
                errorMSg = context?.getString(R.string.invalid_email)
                return errorMSg
            }
            return errorMSg
        }

        fun isValidEmail(target: CharSequence?): Boolean {

            val emailPattern =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
            return target?.toString()?.matches(emailPattern.toRegex()) ?: false
        }

    }

}