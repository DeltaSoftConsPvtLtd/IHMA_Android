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
            if (hasSpace(password)) {
                errorMSg = context?.getString(R.string.space_pasword)
                return errorMSg
            }
            if (!hasNumericValues(password)) {
                errorMSg = context?.getString(R.string.one_numeric)
                return errorMSg
            }
            if (!containsAtleastOneAlphabet(password)) {
                errorMSg = context?.getString(R.string.at_least_one_alphabet)
                return errorMSg
            }
            if (!isValidLength(password, 6)) {
                errorMSg = context?.getString(R.string.password_length)
                return errorMSg
            }
            return errorMSg
        }

        private fun containsAtleastOneAlphabet(s: String?): Boolean {

            if (s != null) {
                return s.matches(".*[a-zA-Z]+.*".toRegex())
            }
            return false
        }

        fun hasSpace(value: String?): Boolean {
            if (value != null) {
                return value.contains(" ")
            }
            return true
        }

        private fun hasNumericValues(value: String?): Boolean {
            if (value != null) {
                return value.matches(".*\\d+.*".toRegex())
            }
            return true
        }

        fun isValidLength(value: String?, minLength: Int): Boolean {

            if (value != null) {
                if (value.length < minLength) {
                    return false
                }
            }
            return true
        }




        fun isValidUserName(username: String,context:Context?): String? {
            var errorMSg: String? = null
            if (isNullOrEmpty(username)) {
                errorMSg = context?.getString(R.string.email_empty)
                return errorMSg
            }
//            if (!Pattern.matches("[a-zA-Z ]+", username)) {
//                errorMSg = context?.getString(R.string.naem_alphabet_only)
//            }

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