package com.deltasoft.ihma.utilities

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo


class Utility {
    companion object{
        fun checkInternetConnection(ctx: Context?): Boolean {
            val connectivityManager = ctx?.getSystemService(Context.CONNECTIVITY_SERVICE)
            return if (connectivityManager is ConnectivityManager) {
                val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
                networkInfo?.isConnected ?: false
            } else return false
        }



        fun loadJSONFromAsserts(ctx: Context?,fileName: String): String? {
            return ctx?.assets?.open(fileName)?.bufferedReader().use { reader ->
                reader?.readText()
            }
        }


    }
    }





