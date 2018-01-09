package com.vyletel.fparch.core

import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI

/**
 * Created by lukas on 07/01/2018.
 */
interface AsyncRunner {
    fun <T>runAsync(task: () -> T)
}

class AndroidCoroutineAsyncRunner: AsyncRunner {
    override fun <T>runAsync(task: () -> T) {
        launch(UI) {
            asyncTask { task() }.await()
        }
    }
}

class BlockingCoroutineAsyncRunner: AsyncRunner {
    override fun <T>runAsync(task: () -> T) {
        runBlocking {
            asyncTask { task() }.await()
        }
    }
}

fun <T> asyncTask(task: () -> T): Deferred<T> = async(CommonPool) { task() }