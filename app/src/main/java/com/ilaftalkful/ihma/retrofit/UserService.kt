package com.ilaftalkful.ihma.retrofit

import android.app.Application
import com.ilaftalkful.ihma.model.UserLoginResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

interface UserService {
    @FormUrlEncoded
    //login
    @POST("login/")
    fun doSignIn(
        @Field("userName") userName: String,
        @Field("password") password: String,
        ): Observable<Response<UserLoginResponse>>


    companion object Factory {
        fun create(application: Application, isAuth: Boolean): UserService? {
            val retrofit = RetrofitClient.getRetrofitClient(application, isAuth)
            return retrofit!!.create(UserService::class.java)
        }
    }


}