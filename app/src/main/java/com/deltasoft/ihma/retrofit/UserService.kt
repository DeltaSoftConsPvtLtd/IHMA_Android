package com.deltasoft.ihma.retrofit

import android.app.Application
import com.deltasoft.ihma.model.alertModel.AlertResponse
import com.deltasoft.ihma.model.loginmodel.UserLoginResponse
import com.deltasoft.ihma.model.otpModel.UserOTPResponse
import com.deltasoft.ihma.model.otpVerifyModel.UserOTPVerifyResponse
import com.deltasoft.ihma.model.registerModel.RegisterUserDetails
import com.deltasoft.ihma.model.registerModel.UserRegistrationResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

interface UserService {
    //login

    @POST("login/")
    @FormUrlEncoded
    fun doSignIn(
        @Field("username") userName: String,
        @Field("password") password: String
        ): Observable<Response<UserLoginResponse>>


    //Register POST
    @Headers(
        "accept: application/json",
        "Content-Type: application/json"
    )
    @POST("getRegisterDetail/")
    fun doRegisterIn(@Body details: RegisterUserDetails): Observable<Response<UserRegistrationResponse>>

    //Register GET
    @GET("getRegisterDetail/")
    fun getRegisterDetails(): Observable<Response<UserRegistrationResponse>>


    //otp
    @FormUrlEncoded
    @POST("resetpassword/")
    fun doOTPService(
        @Field("email") emailId: String,
    ): Observable<Response<UserOTPResponse>>


    //otpVerification
    @FormUrlEncoded
    @POST("resetpassword/confirm")
    fun doOTPVerificationService(
        @Field("email") emailId: String,
        @Field("token") token: String,
        @Field("otp") otp: String,
    ): Observable<Response<UserOTPVerifyResponse>>


    //Events GET
    @GET("getAlerts/")
    fun doEventService(): Observable<Response<AlertResponse>>

    companion object Factory {
        fun create(application: Application, isAuth: Boolean): UserService? {
            val retrofit = RetrofitClient.getRetrofitClient(application, isAuth)
            return retrofit!!.create(UserService::class.java)
        }
    }


}