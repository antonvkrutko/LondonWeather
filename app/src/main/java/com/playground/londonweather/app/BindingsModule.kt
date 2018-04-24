package com.playground.londonweather.app

import com.playground.londonweather.view.base.ActivityScope
import com.playground.londonweather.view.main.MainActivity
import com.playground.londonweather.view.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Anton Krutko on 04/04/2018.
 *
 */
@Module
abstract class BindingsModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    abstract fun mainActivity(): MainActivity
}