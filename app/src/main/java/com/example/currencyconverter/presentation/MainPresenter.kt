package com.example.currencyconverter.presentation

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.currencyconverter.domain.Interactor
import kotlinx.coroutines.*

@ExperimentalCoroutinesApi
@InjectViewState
class MainPresenter : MvpPresenter<MainView>(), CoroutineScope by MainScope() {

    private val interactor = Interactor()

    fun initData() {
        launch {
            viewState.setLoading()
            val currencies = interactor.getAllCurrencies()
            viewState.setCurrencies(currencies.map { it.id })
        }
    }

    override fun onDestroy() {
        cancel()
    }

}