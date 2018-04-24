package com.playground.londonweather.app

import io.reactivex.Scheduler

interface Schedulers {

    fun io(): Scheduler

    fun main(): Scheduler
}