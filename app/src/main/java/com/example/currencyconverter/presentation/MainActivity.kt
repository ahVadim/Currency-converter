package com.example.currencyconverter.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.example.currencyconverter.R
import com.example.currencyconverter.utils.setGone
import com.example.currencyconverter.utils.setVisible
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity(), MainView {

    val presenter = MainPresenter()

    lateinit var fromDropDownAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachView(this)
        setContentView(R.layout.activity_main)

        setupSpinners()
        setupEditText()
    }

    private fun setupSpinners() {
        fromDropDownAdapter =
            ArrayAdapter(this, R.layout.custom_spinner_item, arrayListOf<String>())
                .apply { setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }

        from_currency_spinner.adapter = fromDropDownAdapter
        from_currency_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                presenter.updateFromCurrency(parent?.selectedItem?.toString())
            }

        }

        to_currency_spinner.adapter = fromDropDownAdapter
        to_currency_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                presenter.updateToCurrency(parent?.selectedItem?.toString())
            }

        }
    }

    private fun setupEditText() {
        from_value_edit_text.addTextChangedListener(onTextChanged = { text, _, _, _ ->
            presenter.updateValue(text.toString())
        })
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
    }

    override fun setResultLoading() {
        result_value.alpha = 0.5f
    }

    override fun setResult(text: String) {
        result_value.alpha = 1f
        result_value.text = text
    }
}
