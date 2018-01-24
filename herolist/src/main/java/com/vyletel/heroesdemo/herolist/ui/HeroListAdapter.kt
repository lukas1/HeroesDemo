package com.vyletel.heroesdemo.herolist.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.vyletel.heroesdemo.herolist.R
import com.vyletel.heroesdemo.herolist.datamodel.HeroList
import kotlinx.android.synthetic.main.hero_list_item_view_holder.view.*

/**
 * Created by lukas on 24/01/2018.
 */
class HeroListAdapter : RecyclerView.Adapter<HeroListItemViewHolder>() {
    var heroes: HeroList = listOf()
        set (value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HeroListItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                    R.layout.hero_list_item_view_holder, parent, false
            )
    )

    override fun getItemCount() = heroes.count()

    override fun onBindViewHolder(holder: HeroListItemViewHolder, position: Int) = with(heroes[position]) {
        holder.heroName.text = name
    }
}

class HeroListItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val heroName: TextView = itemView.heroName
}