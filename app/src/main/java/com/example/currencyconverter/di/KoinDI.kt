package com.example.currencyconverter.di

import com.example.currencyconverter.data.Repository
import com.example.currencyconverter.data.RepositoryImpl
import com.example.currencyconverter.domain.Interactor
import com.example.currencyconverter.domain.InteractorImpl
import com.example.currencyconverter.network.NetworkClient
import com.example.currencyconverter.presentation.MainPresenter
import org.koin.dsl.module

val appModule = module {

    factory { RepositoryImpl(get()) as Repository }
    factory { InteractorImpl(get()) as Interactor }
    factory { MainPresenter(get()) }
    single { NetworkClient() }
}