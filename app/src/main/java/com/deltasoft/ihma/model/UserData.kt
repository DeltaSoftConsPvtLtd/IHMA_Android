package com.deltasoft.ihma.model

class UserData {

    private var status: Int = 0
    var statusMessage: String? = null
    var isSuccess: Boolean? = false
    private var error: ErrorData? = null

    init {
        this.error = null
    }

    fun error(error: ErrorData): UserData {
        this.status = UserStatus.ERROR
        this.error = error
        return this
    }

    fun getStatus(): Int {
        return status
    }

    fun getError(): ErrorData? {
        return error
    }

    fun loginSuccess(): UserData {
        this.status = UserStatus.LOGIN_SUCCESS
        this.error = null
        return this
    }

    fun userLoginFailed(): UserData {
        this.status = UserStatus.USER_LOGIN_FAILED
        this.error = null
        return this
    }



    fun buttonClicked(): UserData {
        this.status = UserStatus.CLICKED
        this.error = null
        return this
    }

    fun responseSuccess(): UserData {
        this.status = UserStatus.RESPOSNSE_SUCCESS
        this.error = null
        return this
    }

    fun processing(): UserData {
        this.status = UserStatus.OPERATION_STARTED
        this.error = null
        return this
    }

    fun registrationSuccess(): UserData {
        this.status = UserStatus.REGISTRATION_SUCCESS
        this.error = null
        return this
    }

    fun registrationFailed(): UserData {
        this.status = UserStatus.USER_REGISTRATION_FAILED
        this.error = null
        return this
    }

    fun sessionExpired(): UserData {
        this.status = UserStatus.SESSION_EXPIRED
        this.error = null
        return this
    }

    fun OTPSuccess(): UserData? {
        this.status = UserStatus.USER_OTP_SUCCESS
        this.error = null
        return this

    }

    fun OTPFailed(): UserData? {
        this.status = UserStatus.USER_OTP_FAILED
        this.error = null
        return this

    }

    fun OTPVerificationSuccess(): UserData? {
        this.status = UserStatus.USER_OTP_VERIFICATION_SUCCESS
        this.error = null
        return this
    }

    fun OTPVerificationFailed(): UserData? {
        this.status = UserStatus.USER_OTP_vERIFICATION_FAILED
        this.error = null
        return this

    }

    fun EmptyEvents(): UserData? {
        this.status = UserStatus.EMPTY_EVENTS
        this.error = null
        return this
    }

    fun ViewingEventsFailed(): UserData? {
        this.status = UserStatus.EVENTS_FAILED
        this.error = null
        return this
    }
    fun EmptyFlashNews(): UserData? {
        this.status = UserStatus.EMPTY_FLASHNEWS
        this.error = null
        return this
    }

    fun ViewingFlashNewsFailed(): UserData? {
        this.status = UserStatus.FLASHNEWS_FAILED
        this.error = null
        return this
    }
    fun EmptyNotification(): UserData? {
        this.status = UserStatus.EMPTY_NOTIFICATION
        this.error = null
        return this
    }

    fun ViewingNotificationFailed(): UserData? {
        this.status = UserStatus.NOTIFICATIONS_FAILED
        this.error = null
        return this
    }

    interface UserStatus {
        companion object {

            val RESPOSNSE_SUCCESS = 100
            val CLICKED = 999
            val ERROR = 1000
            val LOGIN_SUCCESS = 1001
            val OPERATION_STARTED=1002
            val USER_LOGIN_FAILED=1113
            val REGISTRATION_SUCCESS = 1003
            val SESSION_EXPIRED = 99
            val USER_REGISTRATION_FAILED=1114
            val USER_OTP_SUCCESS=101
            val USER_OTP_FAILED =102
            val USER_OTP_VERIFICATION_SUCCESS=103
            val USER_OTP_vERIFICATION_FAILED =104
            val EMPTY_EVENTS=105
            val EVENTS_FAILED=106
            val EMPTY_FLASHNEWS=107
            val FLASHNEWS_FAILED=108
            val EMPTY_NOTIFICATION=109
            val NOTIFICATIONS_FAILED=110

        }
    }


}