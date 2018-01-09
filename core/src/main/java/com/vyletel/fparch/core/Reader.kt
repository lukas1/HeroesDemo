package com.vyletel.fparch.core

/**
 * Created by lukas on 06/01/2018.
 */
class Reader<Context, out Result> private constructor(private val computation: (Context) -> Result) {
    companion object {
        fun <Context> ask() = Reader<Context, Context> { it -> it }
    }

    fun <TransformResult> map(transformation: (Result) -> TransformResult) = Reader<Context, TransformResult> {
        transformation(computation(it))
    }

    fun <TransformResult>flatMap(transformation: (Context) -> Reader<Context, TransformResult>) = Reader<Context, TransformResult> {
        run(it)
        transformation(it).run(it)
    }

    fun run(context: Context) = computation(context)
}