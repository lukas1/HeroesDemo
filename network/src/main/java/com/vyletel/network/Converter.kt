package com.vyletel.network

/**
 * Created by lukas on 23/01/2018.
 */
interface Converter<in Input, out Output> {
    fun convert(input: Input): Output
}