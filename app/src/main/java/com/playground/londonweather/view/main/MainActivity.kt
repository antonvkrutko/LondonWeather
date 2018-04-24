package com.playground.londonweather.view.main

import com.playground.londonweather.R
import com.playground.londonweather.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainActivityPresenter>(), MainContract.View {

    override val contentLayout: Int = R.layout.activity_main

    override fun setTemperature(temperature: String) {
        temp_val.text = temperature
    }

    override fun setPressure(pressure: String) {
        pressure_val.text = pressure
    }

    override fun setHumidity(humidity: String) {
        humidity_val.text = humidity
    }

    override fun setMinTemperature(temperature: String) {
        min_temp_val.text = temperature
    }

    override fun setMaxTemperature(temperature: String) {
        max_temp_val.text = temperature
    }

    override fun setError(error: String) {
        error_text.text = error
    }
}
