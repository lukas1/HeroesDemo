package com.vyletel.fparch.core

import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI

/**
 * Created by lukas on 07/01/2018.
 */
interface AsyncRunner {
    fun <T>runAsync(mainThreadTask: (T) -> Unit, asyncResultTask: () -> T)
}

class AndroidCoroutineAsyncRunner: AsyncRunner {
    override fun <T>runAsync(mainThreadTask: (T) -> Unit, asyncResultTask: () -> T) {
        launch(UI) {
            mainThreadTask(asyncTask { asyncResultTask() }.await())
        }
    }
}

class BlockingCoroutineAsyncRunner: AsyncRunner {
    override fun <T>runAsync(mainThreadTask: (T) -> Unit, asyncResultTask: () -> T) {
        runBlocking {
            mainThreadTask(asyncTask { asyncResultTask() }.await())
        }
    }
}

fun <T> asyncTask(task: () -> T): Deferred<T> = async(CommonPool) { task() }