package com.example.currencyconverter.domain

import com.example.currencyconverter.data.Repository
import com.example.currencyconverter.data.RepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InteractorImpl(private val repository: Repository): Interactor {

    override suspend fun getAllCurrencies() = withContext(Dispatchers.IO) {
        repository.getCurrencies()
    }

    override suspend fun convert(value: Double?, from: String, to: String): Double? {
        return if (value != null && from.isNotBlank() && to.isNotBlank()) {
            repository.getConversionCoefficient(from, to)?.times(value)
        } else {
            null
        }
    }
}