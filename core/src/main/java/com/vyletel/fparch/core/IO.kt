package com.vyletel.fparch.core

/**
 * Created by lukas on 05/01/2018.
 */
sealed class IO<out Result>(protected val ioBlock: () -> Result) {

    abstract fun <TransformResult>map(block: (Result) -> TransformResult): IO<TransformResult>

    fun runSync() = ioBlock()

    fun runAsync(asyncRunner: AsyncRunner, completionBlock: (Result) -> Unit) =
            asyncRunner.runAsync(completionBlock, ioBlock)

    class Pure<out Result>(value: Result) : IO<Result>({ value }) {
        override fun <TransformResult> map(block: (Result) -> TransformResult): IO<TransformResult> =
                Pure(block(ioBlock()))
    }

    class Async<out Result>(ioBlock: () -> Result) : IO<Result>(ioBlock) {
        override fun <TransformResult> map(block: (Result) -> TransformResult): IO<TransformResult> =
                Async { block(ioBlock()) }
    }
}