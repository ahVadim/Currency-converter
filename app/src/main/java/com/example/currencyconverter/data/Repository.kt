package com.example.currencyconverter.data

import com.example.currencyconverter.entity.Currency
import com.example.currencyconverter.network.NetworkClient

class Repository {
    suspend fun getCurrencies(): List<Currency> {
        return NetworkClient.api
            .getSupportedCurrencies()
            .currencies.values.toList()
    }

    suspend fun getConversionCoefficient(from: String, to: String): Double? {
        val pair = from + "_" + to
        return NetworkClient.api
            .convert(from + "_" + to)
            .get(pair)
    }
}