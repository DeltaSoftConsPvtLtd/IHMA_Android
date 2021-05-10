package com.ilaftalkful.ihma.utilities

import android.content.Context
import android.util.Patterns
import com.ilaftalkful.ihma.R
import java.util.regex.Pattern

class IhmaValidator(context: Context) {

    companion object {


        fun validateUserPasswordLength(password: String?): Boolean {
            var minLength = 6

            return isValidLength(password, minLength)
        }

        fun isValidUserPassword(password: String?, context: Context?): String? {
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

        fun isValidEmails(email: String, context: Context?): String? {
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

        fun isValidUserName(email: String, context: Context?): String? {
            var errorMSg: String? = null
            if (isNullOrEmpty(email)) {
                errorMSg = context?.getString(R.string.email_empty)
                return errorMSg
            }
            if (!isValidEmail(email)) {
                errorMSg = context?.getString(R.string.invalid_email)
                return errorMSg
            }
            val separated =
                email.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

            if (separated == null || separated.size < 2 || separated[separated.size - 1].length < 2) {
                errorMSg = context?.getString(R.string.invalid_email)
                return errorMSg
            }

            if (Character.isWhitespace(email[0]) || Character.isWhitespace(email[email.length - 1])) {
                errorMSg = context?.getString(R.string.invalid_email)
                return errorMSg
            }

            return errorMSg
        }

        fun isNullOrEmpty(str: String?): Boolean {
            if (str != null && !str.isEmpty())
                return false
            return true
        }

        fun isValidEmail(target: CharSequence?): Boolean {

            val emailPattern =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
            return target?.toString()?.matches(emailPattern.toRegex()) ?: false
        }

        fun userNameContainsSpecialCharacter(value: String): Boolean {
            var p = Pattern.compile("[^a-z0-9@ ]", Pattern.CASE_INSENSITIVE)
            val m = p.matcher(value)
            val b = m.find()
            return b
        }


    }
}



