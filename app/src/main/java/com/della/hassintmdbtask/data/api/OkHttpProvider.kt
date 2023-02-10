package com.della.hassintmdbtask.data.api

import com.della.hassintmdbtask.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

/**
 *The class OkHttpProvider is an object that provides a singleton instance of OkHttpClient.
 *
 * Here's what the code does :
 *
 * 1. Creates an instance of HttpLoggingInterceptor, which is used to log HTTP network requests and response.
 *
 * 2. Builds the OkHttpClient instance using the OkHttpClient.Builder class. The builder is configured with two interceptors:
 *
 *
 *      a. The first interceptor adds the API key as a query parameter to all requests. The API key is taken from the BuildConfig.API_KEY field.
 *
 *
 *      b. The second interceptor is the okHttpLogger instance, which logs network requests and responses.


 */
object OkHttpProvider {
    val instance : OkHttpClient

    init {
        val okHttpLogger = HttpLoggingInterceptor{
            Timber.d(it)
        }
        okHttpLogger.level = HttpLoggingInterceptor.Level.BASIC

        instance = OkHttpClient.Builder().addInterceptor {chain ->
            val originalRequest = chain.request()
            val originalUrl = originalRequest.url

            val url = originalUrl.newBuilder()
                .addQueryParameter("api_key",BuildConfig.API_KEY)
                .build()

            val request = originalRequest.newBuilder().url(url).build()
            chain.proceed(request)
        }.addInterceptor(okHttpLogger).build()
    }
}