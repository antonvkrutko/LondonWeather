package com.playground.londonweather.app

import android.app.Application
import com.facebook.stetho.Stetho


/**
 * Created by Anton Krutko on 04/04/2018.
 *
 */
class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build().apply { inject(this@App) }
    }
}
