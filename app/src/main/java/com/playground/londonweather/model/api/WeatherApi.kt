package com.playground.londonweather.model.api

import com.playground.londonweather.model.entity.CurrentWeather
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Anton Krutko on 09/04/2018.
 *
 */
interface WeatherApi {

    @GET("weather?units=metric")
    fun weatherIn(@Query("q") city: String): Observable<CurrentWeather>

}