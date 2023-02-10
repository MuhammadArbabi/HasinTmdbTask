package com.della.hassintmdbtask.data.api

import android.util.Log
import com.della.hassintmdbtask.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

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