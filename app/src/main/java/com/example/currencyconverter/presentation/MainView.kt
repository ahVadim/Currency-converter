package com.example.currencyconverter.presentation

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface MainView : MvpView {
    fun setLoading()
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showError(text: String)
    fun setCurrencies(names: List<String>)
    fun setResultLoading()
    fun setResult(text: String)
}