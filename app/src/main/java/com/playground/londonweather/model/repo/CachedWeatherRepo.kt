package com.playground.londonweather.model.repo

import com.playground.londonweather.model.entity.Conditions
import io.reactivex.Single

/**
 * Created by Anton Krutko on 13/04/2018.
 *
 */
interface CachedWeatherRepo {
    fun conditionsIn(city: String, refresh: Boolean): Single<Conditions>
}
