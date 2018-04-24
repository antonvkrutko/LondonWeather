package com.playground.londonweather.view.base

import java.lang.ref.WeakReference

/**
 * Created by Anton Krutko on 10/04/2018.
 *
 */
abstract class MvpPresenter<T> : ViewPresenter<T> {

    private var _view = WeakReference<T>(null)

    protected val view: T?
        get() = _view.get()

    override fun attachView(view: T, restored: Boolean) {
        this._view = WeakReference(view)
        onViewAttached(restored)
    }

    override fun detachView() {
        this._view.clear()
        onViewDetached()
    }

    protected open fun onViewAttached(restored: Boolean) {}

    protected open fun onViewDetached() {}
}