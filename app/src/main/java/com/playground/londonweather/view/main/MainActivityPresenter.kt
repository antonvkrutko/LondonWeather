package com.playground.londonweather.view.main

import com.playground.londonweather.app.Schedulers
import com.playground.londonweather.model.repo.CachedWeatherRepo
import com.playground.londonweather.view.base.BasePresenter
import javax.inject.Inject

/**
 * Created by Anton Krutko on 10/04/2018.
 *
 */
class MainActivityPresenter @Inject constructor(
        private val weatherRepo: CachedWeatherRepo,
        private val schedulers: Schedulers
) : BasePresenter<MainContract.View>(), MainContract.Presenter {

    override fun onViewAttached(restored: Boolean) {
        requestWeather(restored)
    }

    private fun requestWeather(restored: Boolean) {
        subscribeWith {
            weatherRepo.conditionsIn("London", !restored)
                    .subscribeOn(schedulers.io())
                    .observeOn(schedulers.main())
                    .subscribe(
                            { conditions ->
                                view?.setTemperature(conditions.temperature.toString())
                                view?.setPressure(conditions.pressure.toString())
                                view?.setHumidity(conditions.humidity.toString())
                                view?.setMaxTemperature(conditions.temperatureMax.toString())
                                view?.setMinTemperature(conditions.temperatureMin.toString())
                            },
                            { error ->
                                view?.setError(error?.message ?: "Unknown error")
                            })
        }
    }
}