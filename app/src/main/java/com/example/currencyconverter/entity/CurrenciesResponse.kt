package com.example.currencyconverter.entity

import com.google.gson.annotations.SerializedName

data class CurrenciesResponse(
    @SerializedName("results") val currencies: Map<String, Currency>
)