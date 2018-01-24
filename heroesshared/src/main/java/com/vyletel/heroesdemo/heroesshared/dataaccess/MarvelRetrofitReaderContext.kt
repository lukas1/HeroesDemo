package com.vyletel.heroesdemo.heroesshared.dataaccess

import com.vyletel.network.Converter
import com.vyletel.network.RetrofitDataSourceContext
import retrofit2.Call
import retrofit2.Retrofit

/**
 * Created by lukas on 24/01/2018.
 */
data class MarvelRetrofitReaderContext<Service, Response, out DomainData>(
        override val service: Class<Service>,
        override val responseConverter: Converter<Response, DomainData>,
        override val executeCallOnService: (Service) -> Call<Response>
): RetrofitDataSourceContext<Service, Response, DomainData> {
    override val retrofit: Retrofit
        get() = marvelRetrofit
}