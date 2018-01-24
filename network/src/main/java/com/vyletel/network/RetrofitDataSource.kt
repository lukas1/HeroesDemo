package com.vyletel.network

import com.vyletel.fparch.core.Either
import com.vyletel.fparch.core.Reader
import retrofit2.Call
import retrofit2.Retrofit

/**
 * Created by lukas on 23/01/2018.
 */
interface RetrofitDataSourceContext<Service, Response, out DomainModel> {
    val retrofit: Retrofit
    val service: Class<Service>
    val responseConverter: Converter<Response, DomainModel>
    val executeCallOnService: (Service) -> Call<Response>
}

fun <Service, Response, DomainModel> fetchDataViaRetrofit() = Reader
        .ask<RetrofitDataSourceContext<Service, Response, DomainModel>>()
        .map { context ->
            try {
                executeRequest(context)?.let {
                    convertAndWrap(context.responseConverter, it)
                } ?: Either.Fail<DataFetchingError, DomainModel>(DataFetchingError.NetworkError)
            } catch (_: Exception) {
                Either.Fail<DataFetchingError, DomainModel>(DataFetchingError.NetworkError)
            }
        }

private fun <Service, Response, DomainModel> executeRequest(
        context: RetrofitDataSourceContext<Service, Response, DomainModel>
) = context.executeCallOnService(createService(context)).execute().body()

private fun <Service>createService(context: RetrofitDataSourceContext<Service, *, *>) = context.retrofit.create(context.service)

private fun <Response, DomainModel> convertAndWrap(
        responseConverter: Converter<Response, DomainModel>, response: Response
) = Either.Success<DataFetchingError, DomainModel>(responseConverter.convert(response))