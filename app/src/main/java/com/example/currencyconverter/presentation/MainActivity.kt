package com.example.currencyconverter.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.example.currencyconverter.R
import com.example.currencyconverter.utils.setGone
import com.example.currencyconverter.utils.setVisible
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity(), MainView {

    val presenter = MainPresenter()

    lateinit var fromDropDownAdapter: ArrayAdapter<String>
    lateinit var toDropDownAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachView(this)
        setContentView(R.layout.activity_main)

        fromDropDownAdapter = createSpinnerAdapter()
        toDropDownAdapter = createSpinnerAdapter()
        from_currency_spinner.adapter = fromDropDownAdapter
        to_currency_spinner.adapter = fromDropDownAdapter
    }

    override fun onStart() {
        super.onStart()
        presenter.initData()
    }

    override fun setLoading() {
        progress.setVisible()
        main_screen.setGone()
    }

    override fun setCurrencies(names: List<String>) {

        progress.setGone()
        main_screen.setVisible()

        fromDropDownAdapter.clear()
        fromDropDownAdapter.addAll(names)
        toDropDownAdapter.clear()
        toDropDownAdapter.addAll(names)
    }

    private fun createSpinnerAdapter() = ArrayAdapter<String>(
        this,
        R.layout.custom_spinner_item,
        arrayListOf<String>()
    ).apply {
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }
}
