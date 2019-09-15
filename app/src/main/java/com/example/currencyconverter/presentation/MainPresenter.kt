package com.example.currencyconverter.presentation

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.currencyconverter.domain.Interactor
import com.example.currencyconverter.domain.InteractorImpl
import kotlinx.coroutines.*
import kotlinx.coroutines.NonCancellable.cancel
import org.json.JSONObject
import retrofit2.HttpException
import java.lang.Exception
import java.net.SocketTimeoutException
import java.net.UnknownHostException

@ExperimentalCoroutinesApi
@InjectViewState
class MainPresenter(private val interactor: Interactor) : MvpPresenter<MainView>(), CoroutineScope by MainScope() {

    private var convertJob: Job? = null
    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        (throwable as? HttpException)?.response()?.errorBody()?.string()?.let {
            try {
                viewState.showError(JSONObject(it).get("error").toString())
                return@CoroutineExceptionHandler
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        if (throwable is SocketTimeoutException ||
                throwable is UnknownHostException) viewState.showError("Нет соединеня с интернетом")
    }

    private var fromCurrency = ""
    private var toCurrency = ""
    private var value: Double? = null

    fun initData() {
        launch(errorHandler) {
            viewState.setLoading()
            val currencies = interactor.getAllCurrencies()
            viewState.setCurrencies(currencies.map { it.id })
            convert()
        }
    }

    private fun convert() {
        convertJob?.cancel()
        convertJob = launch(errorHandler) {
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