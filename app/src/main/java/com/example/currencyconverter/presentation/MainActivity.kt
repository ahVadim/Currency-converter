package com.example.currencyconverter.presentation

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.currencyconverter.R
import com.example.currencyconverter.utils.setGone
import com.example.currencyconverter.utils.setVisible
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import org.koin.android.ext.android.inject

@ExperimentalCoroutinesApi
class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun provide(): MainPresenter {
        val mainPresenter: MainPresenter by inject()
        return mainPresenter
    }

    lateinit var fromDropDownAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupSpinners()
        setupEditText()

        if (savedInstanceState == null) presenter.initData()
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

    override fun setLoading() {
        progress.setVisible()
        main_screen.setGone()
    }

    override fun showError(text: String) {
        Snackbar.make(root_view, text, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.repeat) {
                presenter.initData()
            }.show()
    }

    override fun setCurrencies(names: List<String>) {

        progress.setGone()
        main_screen.setVisible()

        fromDropDownAdapter.clear()
        fromDropDownAdapter.addAll(names)
    }

    override fun setResultLoading() {
        result_progress.setVisible()
        result_value.alpha = 0.5f
    }

    override fun setResult(text: String) {
        result_progress.setGone()
        result_value.alpha = 1f
        result_value.text = text
    }
}
