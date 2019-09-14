package com.example.currencyconverter.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.example.currencyconverter.R
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
        spinner_from.adapter = fromDropDownAdapter
    }



    val handler = CoroutineExceptionHandler { _, e ->
        Log.e("http: ", e.toString())
    }

    override fun onStart() {
        super.onStart()
        button.setOnClickListener {
            presenter.initData()
        }
    }

    override fun setCurrencies(names: List<String>) {
        fromDropDownAdapter.clear()
        fromDropDownAdapter.addAll(names)
    }

    private fun createSpinnerAdapter() = ArrayAdapter<String>(
        this,
        android.R.layout.simple_spinner_item,
        arrayListOf<String>()
    ).apply {
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }
}
