package com.example.currencyconverter.presentation

import com.arellomobile.mvp.MvpView

interface MainView: MvpView {
    fun setCurrencies(names: List<String>)
}