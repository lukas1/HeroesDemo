package com.vyletel.heroesdemo.herolist.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager

import com.vyletel.heroesdemo.herolist.R
import com.vyletel.heroesdemo.herolist.presenter.HeroListPresenter.HeroListView
import com.vyletel.heroesdemo.herolist.datamodel.HeroList
import com.vyletel.heroesdemo.herolist.presenter.HeroListPresenter
import com.vyletel.heroesdemo.herolist.presenter.heroListPresenterFactory
import com.vyletel.loadermvp.MvpActivity
import com.vyletel.network.DataFetchingError
import kotlinx.android.synthetic.main.hero_list_activity.*

class HeroListActivity : MvpActivity<HeroListView, HeroListPresenter>(), HeroListView {
    override val presenterFactory: () -> HeroListPresenter
        get() = heroListPresenterFactory

    override val presenterView: HeroListView
        get() = this

    private val adapter = HeroListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hero_list_activity)

        val layoutManager = LinearLayoutManager(this)
        heroesRecyclerView.layoutManager = layoutManager
        heroesRecyclerView.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation))
        heroesRecyclerView.adapter = adapter
    }

    override fun drawHeroes(heroes: HeroList) {
        adapter.heroes = heroes
    }

    override fun showError(error: DataFetchingError) {
        Snackbar.make(
                heroesRecyclerView,
                stringResourceForDataFetchingError(error),
                Snackbar.LENGTH_LONG
        ).show()
    }

    private fun stringResourceForDataFetchingError(error: DataFetchingError) = when(error) {
        DataFetchingError.ConversionError -> R.string.conversion_error
        DataFetchingError.NetworkError -> R.string.network_error
    }
}