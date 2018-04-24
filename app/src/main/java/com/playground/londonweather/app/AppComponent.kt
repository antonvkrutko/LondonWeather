package com.playground.londonweather.app

import android.app.Activity
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.DispatchingAndroidInjector

/**
 * Created by Anton Krutko on 04/04/2018.
 *
 */
@AppScope
@Component(modules = arrayOf(AppModule::class, BindingsModule::class, AndroidInjectionModule::class))
interface AppComponent {

    fun inject(instance: App)

    val activityInjector: DispatchingAndroidInjector<Activity>
}