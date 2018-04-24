package com.playground.londonweather.model.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by Anton Krutko on 09/04/2018.
 *
 */
data class CurrentWeather(
        @SerializedName("main") val conditions: Conditions
)
