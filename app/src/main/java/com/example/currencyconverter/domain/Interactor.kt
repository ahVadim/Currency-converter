package com.example.currencyconverter.domain

import com.example.currencyconverter.entity.Currency

interface Interactor {

    suspend fun getAllCurrencies(): List<Currency>

    suspend fun convert(value: Double?, from: String, to: String): Double?
}