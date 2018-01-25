package com.vyletel.heroesdemo.herolist.presenter

import com.vyletel.fparch.core.AsyncRunner
import com.vyletel.fparch.core.BlockingCoroutineAsyncRunner
import com.vyletel.fparch.core.Either
import com.vyletel.heroesdemo.heroesshared.dataaccess.datamodels.PagingData
import com.vyletel.heroesdemo.herolist.presenter.HeroListPresenter.HeroListView
import com.vyletel.heroesdemo.herolist.datamodel.HeroId
import com.vyletel.heroesdemo.herolist.datamodel.HeroList
import com.vyletel.heroesdemo.herolist.datamodel.HeroListItem
import com.vyletel.heroesdemo.herolist.readercontexts.HeroListContext
import com.vyletel.heroesdemo.herolist.dataaccess.HeroListDataSource
import com.vyletel.heroesdemo.herolist.readercontexts.HeroListResultHandler
import com.vyletel.network.DataFetchingError
import org.junit.Assert.*
import org.junit.Test

class HeroListPresenterTest {
    @Test
    fun shouldDrawHeroListWith3Items() {
        preparePresenter(
                object : HeroListDataSource {
                    override fun fetchData() = Either.Success<DataFetchingError, HeroList>(HeroList(
                            PagingData(),
                            listOf(
                                    HeroListItem(HeroId("1"), "A"),
                                    HeroListItem(HeroId("1"), ""),
                                    HeroListItem(HeroId("1"), "B"),
                                    HeroListItem(HeroId("1"), ""),
                                    HeroListItem(HeroId("1"), "C")
                            )
                    ))
                }
        ).attachView(object : HeroListView {
            override fun drawHeroes(heroes: HeroList) {
                assertEquals(heroes.values.count(), 3)
                assertEquals(heroes.values.first().name, "A")
                assertEquals(heroes.values.last().name, "C")
            }

            override fun showError(error: DataFetchingError) {
                assertTrue(false) // Should not be called
            }
        })
    }
}

private fun preparePresenter(dataSource: HeroListDataSource) = HeroListPresenter().apply {
    attachListContext(mockListContext(dataSource, this))
}

private fun mockListContext(dataSource: HeroListDataSource, presenter: HeroListPresenter) =
        object : HeroListContext {
            override val dataSource: HeroListDataSource
                get() = dataSource
            override val resultHandler: HeroListResultHandler
                get() = presenter
            override val asyncRunner: AsyncRunner
                get() = BlockingCoroutineAsyncRunner()
        }