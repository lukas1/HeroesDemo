package com.vyletel.loadermvp

/**
 * Created by lukas on 05/01/2018.
 */
interface Presenter<in V> {
    fun attachView(view: V)
    fun detachView()
    fun destroy()
}

abstract class BasePresenter<V>: Presenter<V> {

    var view: V? = null
        private set

    override fun attachView(view: V) {
        this.view = view
        onViewAttached()
    }

    override fun detachView() {
        view = null
        onViewDetached()
    }

    override fun destroy() {
        view = null
        onDestroyed()
    }

    open protected fun onViewAttached() {}
    open protected fun onViewDetached() {}
    open protected fun onDestroyed() {}
}