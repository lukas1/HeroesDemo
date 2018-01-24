package com.vyletel.heroesdemo.herolist.presenter

import com.vyletel.fparch.core.Reader
import com.vyletel.heroesdemo.herolist.presenter.HeroListPresenter.HeroListView
import com.vyletel.heroesdemo.herolist.datamodel.HeroList
import com.vyletel.heroesdemo.herolist.readercontexts.HeroListContext
import com.vyletel.heroesdemo.herolist.readercontexts.HeroListContextImpl
import com.vyletel.heroesdemo.herolist.readercontexts.HeroListResultHandler
import com.vyletel.heroesdemo.herolist.usecase.getHeroesUseCase
import com.vyletel.loadermvp.BasePresenter
import com.vyletel.network.DataFetchingError

/**
 * Created by lukas on 05/01/2018.
 */
val heroListPresenterFactory = {
    HeroListPresenter().apply { attachListContext(HeroListContextImpl(this)) }
}

class HeroListPresenter : BasePresenter<HeroListView>(), HeroListResultHandler {

    interface HeroListView {
        fun drawHeroes(heroes: HeroList)
        fun showError()
    }

    private lateinit var listContext: HeroListContext

    override fun onViewAttached() {
        loadHeroes()
    }

    fun attachListContext(context: HeroListContext) {
        listContext = context
    }

    private fun loadHeroes() {
        Reader.ask<HeroListContext>().flatMap { heroListContext ->
            getHeroesUseCase().map { io ->
                io.runAsync(heroListContext.asyncRunner) { eitherHeroes ->
                    eitherHeroes.fold(
                            failCase = heroListContext.resultHandler::showError,
                            successCase = heroListContext.resultHandler::drawHeroes
                    )
                }
            }
        }.run(listContext)
    }

    override fun drawHeroes(heroes: HeroList) {
        view?.drawHeroes(heroes)
    }

    override fun showError(error: DataFetchingError) {
        view?.showError()
    }
}