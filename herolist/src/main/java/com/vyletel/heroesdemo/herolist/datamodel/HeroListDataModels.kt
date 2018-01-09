package com.vyletel.heroesdemo.herolist.datamodel

import com.vyletel.fparch.core.Either

/**
 * Created by lukas on 05/01/2018.
 */
data class HeroId(val value: String)

data class HeroListItem(val heroId: HeroId, val name: String)

enum class HeroError {
    UNKNOWN
}

typealias HeroList = List<HeroListItem>

typealias FetchHeroListResult = Either<HeroError, HeroList>