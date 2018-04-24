package com.playground.londonweather.model.repo

import com.playground.londonweather.model.entity.Conditions
import com.playground.londonweather.model.persistent.ConditionsDao
import com.playground.londonweather.model.persistent.ConditionsModel
import io.reactivex.Maybe
import io.reactivex.Single

/**
 * Created by Anton Krutko on 12/04/2018.
 *
 */
class CachedOpenWeatherRepo(
        private val originalRepo: WeatherRepo,
        private val conditionsDao: ConditionsDao
) : CachedWeatherRepo {

    override fun conditionsIn(city: String, refresh: Boolean): Single<Conditions> {
        return if (refresh) {
            Maybe.fromAction<ConditionsModel> { conditionsDao.delete(city) }
        } else {
            conditionsDao.load(city)
        }.map { it.toEntity() }
                .switchIfEmpty(originalRepo.conditionsIn(city))
                .doAfterSuccess { conditionsDao.insertOrUpdate(it.toModel(city)) }
    }

    private fun Conditions.toModel(city: String): ConditionsModel {
        return ConditionsModel(
                cityName = city,
                temperature = temperature,
                pressure = pressure,
                humidity = humidity,
                temperatureMax = temperatureMax,
                temperatureMin = temperatureMin
        )
    }

    private fun ConditionsModel.toEntity(): Conditions {
        return Conditions(
                temperature,
                pressure,
                humidity,
                temperatureMax,
                temperatureMin
        )
    }
}

