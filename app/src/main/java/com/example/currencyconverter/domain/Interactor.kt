package com.example.currencyconverter.domain

import com.example.currencyconverter.data.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Interactor {

    private val repository = Repository()

    suspend fun getAllCurrencies() = withContext(Dispatchers.IO){
        repository.getCurrencies()
    }
}