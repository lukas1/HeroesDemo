package com.vyletel.heroesdemo.herolist.readercontexts

import com.vyletel.fparch.core.AndroidCoroutineAsyncRunner
import com.vyletel.fparch.core.AsyncRunner
import com.vyletel.heroesdemo.heroesshared.dataaccess.MarvelRetrofitReaderContext
import com.vyletel.heroesdemo.herolist.datamodel.HeroList
import com.vyletel.heroesdemo.herolist.dataaccess.HeroListDataSource
import com.vyletel.heroesdemo.herolist.dataaccess.HeroListResponse
import com.vyletel.heroesdemo.herolist.dataaccess.HeroListResponseConverter
import com.vyletel.heroesdemo.herolist.dataaccess.HeroListService
import com.vyletel.network.DataFetchingError
import com.vyletel.network.fetchDataViaRetrofit

/**
 * Created by lukas on 06/01/2018.
 */
val heroListRetrofitContext = MarvelRetrofitReaderContext(
        HeroListService::class.java,
        HeroListResponseConverter(),
        { it.getHeroes() }
)

interface HeroListContext {
    val dataSource: HeroListDataSource
    val resultHandler: HeroListResultHandler
    val asyncRunner: AsyncRunner
}

interface HeroListResultHandler {
    fun drawHeroes(heroes: HeroList)
    fun showError(error: DataFetchingError)
}

class HeroListContextImpl(override val resultHandler: HeroListResultHandler) : HeroListContext {
    override val dataSource: HeroListDataSource
        get() = object : HeroListDataSource {
            override fun fetchData() = fetchDataViaRetrofit<HeroListService, HeroListResponse, HeroList>().run(heroListRetrofitContext)
        }

    override val asyncRunner: AsyncRunner
        get() = AndroidCoroutineAsyncRunner()

}