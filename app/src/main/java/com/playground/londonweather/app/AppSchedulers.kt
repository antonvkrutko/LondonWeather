package com.playground.londonweather.app

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class AppSchedulers : Schedulers {

    override fun io(): Scheduler = io.reactivex.schedulers.Schedulers.io()

    override fun main(): Scheduler = AndroidSchedulers.mainThread()
}