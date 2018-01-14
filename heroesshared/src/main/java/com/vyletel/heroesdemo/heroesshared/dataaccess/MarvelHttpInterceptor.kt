package com.vyletel.heroesdemo.heroesshared.dataaccess

import com.vyletel.heroesdemo.heroesshared.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.security.MessageDigest

/**
 * Created by lukas on 13/01/2018.
 */
class MarvelHttpInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url()
        val modifiedUrl = originalUrl.newBuilder()
                .addQueryParameter("apikey", BuildConfig.MARVEL_API_PUBLIC_KEY)
                .addQueryParameter("ts", computeTsParameter(
                        System.currentTimeMillis()/1000,
                        BuildConfig.MARVEL_API_PRIVATE_KEY,
                        BuildConfig.MARVEL_API_PUBLIC_KEY
                ))
                .build()

        val modifiedRequest = originalRequest.newBuilder()
                .url(modifiedUrl)
                .build()
        return chain.proceed(modifiedRequest)
    }
}

fun computeTsParameter(timestamp: Long, privateKey: String, publicKey: String) =
    getMD5("$timestamp$privateKey$publicKey")

private fun getMD5(input: String) =
    String(encodeHex(getDigest("MD5", input)))

private fun getDigest(algorithm: String, input: String) =
    MessageDigest.getInstance(algorithm).digest(input.toByteArray())

private fun encodeHex(data: ByteArray): CharArray {
    val toDigits = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')
    val l = data.size
    val out = CharArray(l shl 1)
    var i = 0
    var j = 0
    while (i < l) {
        out[j++] = toDigits[(240 and data[i].toInt()).ushr(4)]
        out[j++] = toDigits[15 and data[i].toInt()]
        i++
    }
    return out
}