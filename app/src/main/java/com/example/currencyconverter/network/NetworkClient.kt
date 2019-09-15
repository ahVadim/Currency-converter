package com.example.currencyconverter.network

import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkClient(context: Context) {

    val cacheSize = (5 * 1024 * 1024).toLong() //5 MB
    val myCache = Cache(context.cacheDir, cacheSize)


    private val client = OkHttpClient.Builder()
        .cache(myCache)
        .addNetworkInterceptor(OnlineCacheInterceptor())
        .addInterceptor(OfflineCacheInterceptor(context))
        .addInterceptor(CurrencyConverterInterceptor())
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    val api: ConverterApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://free.currconv.com")
        .client(client)
        .build()
        .create(ConverterApi::class.java)
}