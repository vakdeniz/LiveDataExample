package com.vakdeniz.livedataexample.networking

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.vakdeniz.livedataexample.model.BlogWrapper
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RestApiService {

    @GET("api/feed.json")
    fun getPopularBlogAsync(): Deferred<BlogWrapper>

    companion object {

        private const val BASE_URL = "https://androidwave.com/"

        fun init(): RestApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build().create(RestApiService::class.java)
        }
    }
}