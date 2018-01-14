package com.vyletel.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by lukas on 09/01/2018.
 */
fun getRetrofit(url: String, httpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(httpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()