package com.example.currencyconverter.data

import com.example.currencyconverter.entity.Currency
import com.example.currencyconverter.network.NetworkClient

class RepositoryImpl(private val networkClient: NetworkClient): Repository {
    override suspend fun getCurrencies(): List<Currency> {
        return networkClient.api
            .getSupportedCurrencies()
            .currencies.values.toList()
    }

    override suspend fun getConversionCoefficient(from: String, to: String): Double? {
        val pair = from + "_" + to
        return networkClient.api
            .convert(from + "_" + to)
            .get(pair)
    }
}