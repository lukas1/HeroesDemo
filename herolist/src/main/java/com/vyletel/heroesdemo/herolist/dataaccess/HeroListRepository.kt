package com.vyletel.heroesdemo.herolist.dataaccess

import com.vyletel.fparch.core.Either
import com.vyletel.fparch.core.IO
import com.vyletel.fparch.core.Reader
import com.vyletel.heroesdemo.herolist.datamodel.FetchHeroListResult
import com.vyletel.heroesdemo.herolist.datamodel.HeroError
import com.vyletel.heroesdemo.herolist.datamodel.HeroList
import com.vyletel.heroesdemo.herolist.readercontexts.HeroListContext

/**
 * Created by lukas on 05/01/2018.
 */
fun fetchHeroes(): Reader<HeroListContext, IO<FetchHeroListResult>> = Reader
        .ask<HeroListContext>()
        .map {
            IO.Async { fetchHeroesAsynchronously(it.dataSource) }
        }

private fun fetchHeroesAsynchronously(dataSource: HeroListDataSource) =
        Either.Success<HeroError, HeroList>(dataSource.fetchData())
