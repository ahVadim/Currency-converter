package com.example.currencyconverter.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.internal.connection.ConnectInterceptor.intercept
import java.io.IOException
import javax.xml.datatype.DatatypeConstants.SECONDS
import okhttp3.CacheControl
import java.util.concurrent.TimeUnit


class OnlineCacheInterceptor : Interceptor {

    companion object {
        const val CACHE_MAX_AGE = 60 //в секундах
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val cacheControl = CacheControl.Builder()
            .maxAge(CACHE_MAX_AGE, TimeUnit.SECONDS)
            .build()
        return response.newBuilder()
            .header("Cache-Control", cacheControl.toString())
            .build()
    }
}
