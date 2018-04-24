package com.playground.londonweather.model.persistent

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Anton Krutko on 12/04/2018.
 *
 */
@Entity
data class ConditionsModel(
        @PrimaryKey
        val cityName: String,

        val temperature: Float,
        val pressure: Float,
        val humidity: Float,
        val temperatureMax: Float,
        val temperatureMin: Float
)