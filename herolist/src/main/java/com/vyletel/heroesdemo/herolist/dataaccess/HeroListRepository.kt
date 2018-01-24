package com.vyletel.heroesdemo.herolist.dataaccess

import com.vyletel.fparch.core.IO
import com.vyletel.fparch.core.Reader
import com.vyletel.heroesdemo.herolist.datamodel.FetchHeroListResult
import com.vyletel.heroesdemo.herolist.readercontexts.HeroListContext

/**
 * Created by lukas on 05/01/2018.
 */
fun fetchHeroes(): Reader<HeroListContext, IO<FetchHeroListResult>> = Reader
        .ask<HeroListContext>()
        .map {
            IO.Async { it.dataSource.fetchData() }
        }
