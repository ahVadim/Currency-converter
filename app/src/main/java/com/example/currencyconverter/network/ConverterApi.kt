package com.example.currencyconverter.network

import com.example.currencyconverter.entity.CurrenciesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ConverterApi {

    @GET("/api/v7/convert")
    suspend fun convert(@Query("q") from: String): Map<String, Double>

    @GET("api/v7/currencies")
    suspend fun getSupportedCurrencies(): CurrenciesResponse

}