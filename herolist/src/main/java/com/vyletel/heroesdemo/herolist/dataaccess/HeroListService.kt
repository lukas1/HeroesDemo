package com.vyletel.heroesdemo.herolist.dataaccess

import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by lukas on 13/01/2018.
 */
interface HeroListService {

    @GET("characters")
    fun getHeroes(): Call<HeroListResponse>

}