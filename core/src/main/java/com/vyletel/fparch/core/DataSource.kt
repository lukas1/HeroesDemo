package com.vyletel.fparch.core

/**
 * Created by lukas on 06/01/2018.
 */
interface DataSourceFromInput<in Input, out Output> {
    fun fetchData(input: Input): Output
}

interface DataSource<out Output> {
    fun fetchData(): Output
}