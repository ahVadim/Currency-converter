package com.example.currencyconverter.presentation

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.currencyconverter.domain.Interactor
import kotlinx.coroutines.*

@ExperimentalCoroutinesApi
@InjectViewState
class MainPresenter : MvpPresenter<MainView>(), CoroutineScope by MainScope() {

    private val interactor = Interactor()
    private var convertJob: Job? = null

    private var fromCurrency = ""
    private var toCurrency = ""
    private var value: Double? = null

    fun initData() {
        launch {
            viewState.setLoading()
            val currencies = interactor.getAllCurrencies()
            viewState.setCurrencies(currencies.map { it.id })
        }
    }

    private fun convert() {
        convertJob?.cancel()
        convertJob = launch {
            viewState.setResultLoading()
            val result = interactor.convert(value, fromCurrency, toCurrency)
            viewState.setResult(result?.toString() ?: "")
        }
    }

    override fun onDestroy() {
        cancel()
    }

    fun updateFromCurrency(fromCurrency: String?) {
        this.fromCurrency = fromCurrency ?: ""
        convert()
    }

    fun updateToCurrency(toCurrency: String?) {
        this.toCurrency = toCurrency ?: ""
        convert()
    }

    fun updateValue(value: String) {
        this.value = value.toDoubleOrNull()
        convert()
    }

}