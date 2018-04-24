package com.playground.londonweather.model.repo

import com.playground.londonweather.model.api.WeatherApi
import com.playground.londonweather.model.entity.Conditions
import io.reactivex.Single

/**
 * Created by Anton Krutko on 09/04/2018.
 *
 */
class OpenWeatherRepo(
        private val weatherApi: WeatherApi
) : WeatherRepo {

    override fun conditionsIn(city: String): Single<Conditions> {
        return weatherApi.weatherIn(city)
                .map { it.conditions }
                .firstOrError()
    }
}
