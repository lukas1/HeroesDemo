package com.vyletel.heroesdemo.herolist.datamodel

import com.vyletel.fparch.core.Either
import com.vyletel.heroesdemo.heroesshared.dataaccess.datamodels.PagingData
import com.vyletel.network.DataFetchingError

/**
 * Created by lukas on 05/01/2018.
 */
data class HeroId(val value: String)

data class HeroListItem(val heroId: HeroId, val name: String)

data class HeroList(
        val pagingData: PagingData,
        val values: List<HeroListItem>
)

typealias FetchHeroListResult = Either<DataFetchingError, HeroList>