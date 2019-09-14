package com.example.currencyconverter.presentation

import com.arellomobile.mvp.MvpView

interface MainView : MvpView {
    fun setLoading()
    fun setCurrencies(names: List<String>)
    fun setResultLoading()
    fun setResult(text: String)
}