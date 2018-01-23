package com.vyletel.heroesdemo.heroesshared.dataaccess

import com.vyletel.network.getRetrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

/**
 * Created by lukas on 13/01/2018.
 */

val loggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BASIC
}

const val MARVEL_API_URL = "http://gateway.marvel.com/v1/public/"
val marvelHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(MarvelHttpInterceptor())
        .build()

val marvelRetrofit: Retrofit = getRetrofit(MARVEL_API_URL, marvelHttpClient)