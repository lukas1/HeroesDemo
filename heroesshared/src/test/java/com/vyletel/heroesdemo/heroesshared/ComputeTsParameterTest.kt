package com.vyletel.heroesdemo.heroesshared

import com.vyletel.heroesdemo.heroesshared.dataaccess.computeTsParameter
import org.junit.Assert.*
import org.junit.Test

/**
 * Created by lukas on 14/01/2018.
 */
class ComputeTsParameterTest {
    @Test
    fun computesTsParameter() {
        val output = computeTsParameter(1505020107,"1", "2")
        assertEquals("5432a077f7eaaf0c1640843ed51aaa31", output)
    }
}