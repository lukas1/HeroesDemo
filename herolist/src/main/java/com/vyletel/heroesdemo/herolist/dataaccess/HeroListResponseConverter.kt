package com.vyletel.heroesdemo.herolist.dataaccess

import com.vyletel.heroesdemo.herolist.datamodel.HeroId
import com.vyletel.heroesdemo.herolist.datamodel.HeroList
import com.vyletel.heroesdemo.herolist.datamodel.HeroListItem
import com.vyletel.network.Converter

/**
 * Created by lukas on 23/01/2018.
 */
class HeroListResponseConverter : Converter<HeroListResponse, HeroList> {
    override fun convert(input: HeroListResponse) = input.data?.results?.mapNotNull {
        it.id?.let { id ->
            it.name?.let { name ->
                HeroListItem(HeroId(id), name)
            }
        }
    } ?: listOf()
}