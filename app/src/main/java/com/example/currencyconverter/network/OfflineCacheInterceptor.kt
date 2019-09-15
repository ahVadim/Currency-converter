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


class OfflineCacheInterceptor(private val context: Context) : Interceptor {

    companion object {
        const val CACHE_MAX_STALE = 60 * 60 * 24 //1 день
    }

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = if (!hasNetwork()) {
            val cacheControl = CacheControl.Builder()
                .maxStale(CACHE_MAX_STALE, TimeUnit.SECONDS)
                .build()
            chain.request().newBuilder()
                .header("Cache-Control", cacheControl.toString())
                .build();
        } else {
            chain.request()
        }

        return chain.proceed(request);
    }

    private fun hasNetwork(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }


}
