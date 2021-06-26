package com.deltasoft.ihma.retrofit

import android.content.Context
import com.google.gson.GsonBuilder
import com.deltasoft.ihma.utilities.Constants
import com.deltasoft.ihma.utilities.IlafSharedPreference
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit


class  RetrofitClient {
    companion object {
        private var instance: Retrofit? = null
        private var mContext: Context? = null
        private var mUrl: String? = null
        var pref:IlafSharedPreference? = null
        private var converterFactory: Converter.Factory? = null
       // val API_BASE_URL_PROD = "https://lmsihma.co.in/api/"
        val API_BASE_URL_PROD = "https://65.1.213.215/api/"
        /**
         * Get retrofit client
         *
         * @param baseUrl
         * @return
         */
        fun getRetrofitClient(
            context: Context,
            isAuth: Boolean? = false,
            converterFactory: Converter.Factory? = null
        ): Retrofit? {
            this.mContext=context
            mUrl = API_BASE_URL_PROD
            this.converterFactory = converterFactory
            val builder = OkHttpClient().newBuilder()

            //for clearing http to https reset error
            builder.protocols(Collections.singletonList(Protocol.HTTP_1_1))

            builder.readTimeout(60, TimeUnit.SECONDS)
            builder.connectTimeout(60, TimeUnit.SECONDS)
//            builder.addInterceptor(LanguageInterceptor())
//            if(isAuth?:false) {
//                builder.addInterceptor(AuthInterceptor())
//            }
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client: OkHttpClient = builder.addInterceptor(interceptor).build()


            instance = Retrofit.Builder()
                .addConverterFactory(getConverterFactory())
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(mUrl)
                .build()
            return instance



        }


        private fun getConverterFactory(): Converter.Factory {
            val gson = GsonBuilder().setExclusionStrategies(ExcludeAnnotationStrategy()).create()
            return converterFactory ?: GsonConverterFactory.create(gson)
        }

        internal class LanguageInterceptor : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                var request: Request = chain.request()
                if (pref != null) { //essentially checking if the prefs has a non null token
                    request = request.newBuilder()
                        .addHeader(
                            "",
                            ""
                                ?: ""
                        )
                        .build()
                }
                return chain.proceed(request)
            }

        }

        internal class AuthInterceptor : Interceptor {
           override fun intercept(chain: Interceptor.Chain): Response {
                var request: Request = chain.request()
                if (pref!= null ) { //essentially checking if the prefs has a non null token
                    request = request.newBuilder()
                            .addHeader(Constants.AUTHORIZATION_KEY, Constants.BEARER_KEY +pref?.getStringPrefValue(IlafSharedPreference.Constants.TOKEN_KEY) ?: "")
                        .build()
                }
                return chain.proceed(request)
            }
        }
    }



}

