package com.playground.londonweather.view.main

import com.playground.londonweather.view.base.ViewPresenter

/**
 * Created by Anton Krutko on 10/04/2018.
 *
 */
interface MainContract {

    interface View {
        fun setTemperature(temperature: String)
        fun setPressure(pressure: String)
        fun setHumidity(humidity: String)
        fun setMinTemperature(temperature: String)
        fun setMaxTemperature(temperature: String)

        fun setError(error: String)
    }

    interface Presenter : ViewPresenter<View>
}