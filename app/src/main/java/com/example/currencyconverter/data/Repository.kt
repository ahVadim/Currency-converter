package com.example.currencyconverter.data

import com.example.currencyconverter.entity.Currency
import com.example.currencyconverter.network.NetworkClient

interface Repository {
    suspend fun getCurrencies(): List<Currency>

    suspend fun getConversionCoefficient(from: String, to: String): Double?
}