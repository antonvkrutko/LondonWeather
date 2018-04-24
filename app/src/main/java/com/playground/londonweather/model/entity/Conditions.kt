package com.playground.londonweather.model.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by Anton Krutko on 09/04/2018.
 *
 */
data class Conditions(
        @SerializedName("temp") val temperature: Float,
        @SerializedName("pressure") val pressure: Float,
        @SerializedName("humidity") val humidity: Float,
        @SerializedName("temp_min") val temperatureMax: Float,
        @SerializedName("temp_max") val temperatureMin: Float
)
