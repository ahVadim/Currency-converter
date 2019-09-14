package com.example.currencyconverter.data

import com.example.currencyconverter.entity.Currency
import com.example.currencyconverter.network.NetworkClient

class Repository {
    suspend fun getCurrencies(): List<Currency> {
        return NetworkClient.api
            .getSupportedCurrencies()
            .currencies.values.toList()
    }
}