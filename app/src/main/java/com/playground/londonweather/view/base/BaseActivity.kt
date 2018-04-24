package com.playground.londonweather.view.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.playground.londonweather.app.App
import javax.inject.Inject

abstract class BaseActivity<P : ViewPresenter<*>> : AppCompatActivity() {

    abstract val contentLayout: Int

    @Inject
    lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(contentLayout)
        (application as App).appComponent.activityInjector.maybeInject(this)
        super.onCreate(savedInstanceState)

        bindView(savedInstanceState != null)
    }

    override fun onDestroy() {
        super.onDestroy()

        unbindView()
    }

    protected open fun onBindView() {}

    protected open fun onUnbindView() {}

    private fun bindView(restored: Boolean) {
        @Suppress("UNCHECKED_CAST")
        (presenter as? ViewPresenter<Any>)?.attachView(this, restored)
        onBindView()
    }

    private fun unbindView() {
        presenter.detachView()
        onUnbindView()
    }
}