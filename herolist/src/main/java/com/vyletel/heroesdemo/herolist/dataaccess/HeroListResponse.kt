package com.vyletel.heroesdemo.herolist.dataaccess

/**
 * Created by lukas on 23/01/2018.
 */
data class HeroListResponse(
        val data: HeroListResponseData?
)

data class HeroListResponseData(
        val results: List<HeroListResponseListItem>?
)

data class HeroListResponseListItem(
        val id: String?,
        val name: String?
)

