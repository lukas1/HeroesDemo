package com.vyletel.heroesdemo.heroesshared.dataaccess.datamodels

/**
 * Created by lukas on 25/01/2018.
 */
data class PagingData(
        val offset: Int = 0,
        val limit: Int = 0,
        val totalItems: Int = 0
)