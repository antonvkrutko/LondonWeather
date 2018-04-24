package com.playground.londonweather.view.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Anton Krutko on 10/04/2018.
 *
 */
abstract class BasePresenter<T> : MvpPresenter<T>() {

    private val compositeDisposable = CompositeDisposable()

    protected fun subscribeWith(block: () -> Disposable) {
        compositeDisposable.add(block())
    }

    protected fun clear() {
        compositeDisposable.clear()
    }

    override fun onViewDetached() {
        clear()
    }

}
