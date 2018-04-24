package com.playground.londonweather.model.repo

import com.playground.londonweather.model.entity.Conditions
import io.reactivex.Single

/**
 * Created by Anton Krutko on 09/04/2018.
 *
 */
interface WeatherRepo {

    fun conditionsIn(city: String): Single<Conditions>
}