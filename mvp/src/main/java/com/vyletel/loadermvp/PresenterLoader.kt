package com.vyletel.loadermvp

import android.content.Context
import android.support.v4.content.Loader

/**
 * Created by lukas on 05/01/2018.
 */
class PresenterLoader<P : Presenter<*>>(
        context: Context,
        private val presenterFactory: () -> P
) : Loader<P>(context) {
    var presenter: P? = null

    override fun onStartLoading() {
        super.onStartLoading()
        presenter?.let(this::deliverResult) ?: forceLoad()
    }

    override fun onForceLoad() {
        super.onForceLoad()
        presenter = presenterFactory.invoke()
        presenter?.let(this::deliverResult)
    }

    override fun onReset() {
        super.onReset()
        presenter?.destroy()
        presenter = null
    }
}