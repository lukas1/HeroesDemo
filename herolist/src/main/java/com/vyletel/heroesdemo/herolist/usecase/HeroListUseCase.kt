package com.vyletel.heroesdemo.herolist.usecase

import com.vyletel.fparch.core.IO
import com.vyletel.fparch.core.Reader
import com.vyletel.heroesdemo.herolist.datamodel.FetchHeroListResult
import com.vyletel.heroesdemo.herolist.datamodel.HeroList
import com.vyletel.heroesdemo.herolist.dataaccess.fetchHeroes
import com.vyletel.heroesdemo.herolist.readercontexts.HeroListContext

/**
 * Created by lukas on 05/01/2018.
 */
fun getHeroesUseCase(): Reader<HeroListContext, IO<FetchHeroListResult>> = fetchHeroes().map { io ->
    io.map { eitherHeroes -> eitherHeroes.map { filterHeroes(it) } }
}

private fun filterHeroes(heroList: HeroList) = heroList.filter {
    !it.name.isEmpty()
}