package com.vyletel.heroesdemo.herolist.readercontexts

import com.vyletel.fparch.core.AndroidCoroutineAsyncRunner
import com.vyletel.fparch.core.AsyncRunner
import com.vyletel.heroesdemo.heroesshared.dataaccess.marvelRetrofit
import com.vyletel.heroesdemo.herolist.datamodel.HeroError
import com.vyletel.heroesdemo.herolist.datamodel.HeroList
import com.vyletel.heroesdemo.herolist.dataaccess.HeroListDataSource
import com.vyletel.heroesdemo.herolist.dataaccess.HeroListService
import com.vyletel.heroesdemo.herolist.datamodel.HeroId
import com.vyletel.heroesdemo.herolist.datamodel.HeroListItem

/**
 * Created by lukas on 06/01/2018.
 */
interface HeroListContext {
    val dataSource: HeroListDataSource
    val resultHandler: HeroListResultHandler
    val asyncRunner: AsyncRunner
}

interface HeroListResultHandler {
    fun drawHeroes(heroes: HeroList)
    fun showError(error: HeroError)
}

class HeroListContextImpl(override val resultHandler: HeroListResultHandler) : HeroListContext {
    override val dataSource: HeroListDataSource
        get() = object : HeroListDataSource {
            override fun fetchData() = marvelRetrofit.create(HeroListService::class.java).getHeroes().execute().body()?.data?.results?.mapNotNull {
                it.id?.let { id ->
                    it.name?.let { name ->
                        HeroListItem(HeroId(id), name)
                    }
                }
            } ?: listOf()
        }

    override val asyncRunner: AsyncRunner
        get() = AndroidCoroutineAsyncRunner()

}