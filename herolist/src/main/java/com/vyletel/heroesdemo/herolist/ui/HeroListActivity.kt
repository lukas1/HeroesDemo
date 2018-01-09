package com.vyletel.heroesdemo.herolist.ui

import android.os.Bundle

import com.vyletel.heroesdemo.herolist.R
import com.vyletel.heroesdemo.herolist.presenter.HeroListPresenter.HeroListView
import com.vyletel.heroesdemo.herolist.datamodel.HeroList
import com.vyletel.heroesdemo.herolist.presenter.HeroListPresenter
import com.vyletel.heroesdemo.herolist.presenter.heroListPresenterFactory
import com.vyletel.loadermvp.MvpActivity

class HeroListActivity : MvpActivity<HeroListView, HeroListPresenter>(), HeroListView {
    override val presenterFactory: () -> HeroListPresenter
        get() = heroListPresenterFactory

    override val presenterView: HeroListView
        get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hero_list_activity)
    }

    override fun drawHeroes(heroes: HeroList) {
        // TODO: Implement
    }

    override fun showError() {
        // TODO: Implement
    }
}