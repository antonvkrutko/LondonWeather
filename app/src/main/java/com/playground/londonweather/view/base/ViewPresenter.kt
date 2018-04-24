package com.playground.londonweather.view.base

/**
 * Created by Anton Krutko on 10/04/2018.
 *
 */
interface ViewPresenter<in T> {

    fun attachView(view: T, restored: Boolean)

    fun detachView()
}
