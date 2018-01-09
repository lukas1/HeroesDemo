package com.vyletel.heroesdemo.herolist.readercontexts

import com.vyletel.fparch.core.AndroidCoroutineAsyncRunner
import com.vyletel.fparch.core.AsyncRunner
import com.vyletel.heroesdemo.herolist.datamodel.HeroError
import com.vyletel.heroesdemo.herolist.datamodel.HeroId
import com.vyletel.heroesdemo.herolist.datamodel.HeroList
import com.vyletel.heroesdemo.herolist.datamodel.HeroListItem
import com.vyletel.heroesdemo.herolist.dataaccess.HeroListDataSource

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
            override fun fetchData() = listOf(
                    HeroListItem(HeroId("AB"), "A"),
                    HeroListItem(HeroId("BA"), "BA"),
                    HeroListItem(HeroId("BB"), "BB"),
                    HeroListItem(HeroId("BC"), "BC"),
                    HeroListItem(HeroId("BA"), "BA")
            )
        }

    override val asyncRunner: AsyncRunner
        get() = AndroidCoroutineAsyncRunner()

}