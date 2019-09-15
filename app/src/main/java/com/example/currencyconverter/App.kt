package com.example.currencyconverter

import android.app.Application
import com.example.currencyconverter.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ConverterApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@ConverterApp)
            modules(appModule)
        }
    }
}