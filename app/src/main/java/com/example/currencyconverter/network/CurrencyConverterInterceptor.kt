package com.example.currencyconverter.network

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.internal.connection.ConnectInterceptor.intercept
import java.io.IOException

class CurrencyConverterInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val original = chain.request()

        val url = original.url.newBuilder()
            .addQueryParameter("apiKey", "7c54c32f7ea75b81c451")
            .addQueryParameter("compact", "ultra")
            .build();

        val request = original.newBuilder()
            .url(url)
            .build()

        return chain.proceed(request);
    }


}
