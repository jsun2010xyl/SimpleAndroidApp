package com.example.jsonkotlin1.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import com.example.jsonkotlin1.data.network.ConnectivityInterceptor
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterceptorImpl(context: Context) : ConnectivityInterceptor {

    private val appContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isOnline()){
            // 下面这句代码如果取消注释就会报错
            //throw Exception()
        }
        return chain.proceed(chain.request())
    }

    private fun isOnline(): Boolean {


        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            return true // TODO: 可能不对

        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected

        }

    }
}