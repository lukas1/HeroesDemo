package com.vyletel.loadermvp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader

const val LOADER_ID = 1

abstract class MvpActivity<V, P: Presenter<V>> : AppCompatActivity() {

    private var presenter: P? = null

    abstract val presenterFactory: () -> P

    abstract val presenterView: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportLoaderManager.initLoader(LOADER_ID, null, object : LoaderManager.LoaderCallbacks<P> {
            override fun onCreateLoader(id: Int, args: Bundle?) = PresenterLoader(
                    this@MvpActivity, presenterFactory
            )

            override fun onLoadFinished(loader: Loader<P>, data: P) {
                presenter = data
            }

            override fun onLoaderReset(loader: Loader<P>) {
                presenter = null
            }

        })
    }

    override fun onStart() {
        super.onStart()
        presenter?.attachView(presenterView)
    }

    override fun onStop() {
        presenter?.detachView()
        super.onStop()
    }
}
