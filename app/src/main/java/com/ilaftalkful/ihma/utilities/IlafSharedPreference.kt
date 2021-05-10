package com.ilaftalkful.ihma.utilities

import android.content.Context

class IlafSharedPreference {

    private val SHARED_PREF_NAME = "IlafPreference"
    internal var mContext: Context? = null

    constructor(context: Context?) {
        this.mContext = context
    }

    /**
     * Setting a boolean key value to Shared preference
     *
     * @param key
     * @param value
     */
    fun setDoublePrefValue(key: String, value: Double?) {
        val pref = mContext!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = pref.edit()
        if (null == value)
            editor.putLong(key, java.lang.Double.doubleToRawLongBits(0.0))
        else
            editor.putLong(key, java.lang.Double.doubleToRawLongBits(value))
        editor.commit()
        return
    }

    fun getDouble(key: String): Double {

        val pref = mContext!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

        return java.lang.Double.longBitsToDouble(pref.getLong(key, java.lang.Double.doubleToLongBits(0.0)))
    }

    /**
     * Getting boolean key value fromDeviceCursor shared preference
     *
     * @param key
     * @return
     */
    fun getBooleanPrefValue(key: String): Boolean {
        if (mContext == null) {
            return false
        }
        val pref = mContext!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

        return pref.getBoolean(key, false)
    }

    /**
     * Getting boolean key value fromDeviceCursor shared preference
     *
     * @param key
     * @return
     */
    fun getBooleanPrefValue2(key: String): Boolean {
        val pref = mContext!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

        return pref.getBoolean(key, true)
    }

    /**
     * Getting boolean key value fromDeviceCursor shared preference
     *
     * @param key
     * @return
     */
    fun getLongPrefValue(key: String): Long {
        val pref = mContext!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        var value: Long = 0
        try {
            value = pref.getLong(key, 0)
            if (0L == value) {
                return -1
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return value
    }

    /**
     * Setting a boolean key value to Shared preference
     *
     * @param key
     * @param value
     */
    fun setBooleanPrefValue(key: String, value: Boolean) {
        val pref = mContext!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = pref.edit()

        editor.putBoolean(key, value)
        editor.commit()

    }

    /**
     * Getting float key value fromDeviceCursor shared preference
     *
     * @param key
     * @return
     */
    fun getFloatPrefValue(key: String): Float {
        val pref = mContext!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

        return pref.getFloat(key, 0.000000.toFloat())
    }

    /**
     * Setting a boolean key value to Shared preference
     *
     * @param key
     * @param value
     */
    fun setFloatPrefValue(key: String, value: Float) {
        val pref = mContext!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = pref.edit()

        editor.putFloat(key, value)
        editor.commit()

    }

    /**
     * Setting a boolean key value to Shared preference
     *
     * @param key
     * @param value
     */
    fun setLongPrefValue(key: String, value: Long?) {
        val pref = mContext!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = pref.edit()

        if (value != null) {
            editor.putLong(key, value)
        }
        editor.commit()

    }

    /**
     * Getting boolean key value fromDeviceCursor shared preference
     *
     * @param key
     * @return
     */
    fun getStringPrefValue(key: String): String? {
        val pref = mContext!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

        return pref.getString(key, null)
    }

    /**
     * Setting a boolean key value to Shared preference
     *
     * @param key
     * @param value
     */
    fun setStringPrefValue(key: String, value: String?) {
        val pref = mContext!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = pref.edit()

        editor.putString(key, value)
        editor.commit()

    }

    /**
     * Getting integer key value fromDeviceCursor shared preference
     *
     * @param key
     * @return
     */
    fun getIntegerPrefValue(key: String): Int {
        val pref = mContext!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

        return pref.getInt(key, -1)
    }

    /**
     * Setting a integer key value to Shared preference
     *
     * @param key
     * @param value
     */
    fun setIntegerPrefValue(key: String, value: Int?) {
        val pref = mContext!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = pref.edit()

        value?.let { editor.putInt(key, it) }
        editor.commit()
    }

    fun clear() {
        val settings = mContext!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        settings.edit().clear().commit()
    }

    interface Constants {
        companion object {
            val IS_GUEST_LOGIN="guest_login"
            val LANGUAGE_KEY="language"
            val TOKEN_KEY="token"
        }
    }
}