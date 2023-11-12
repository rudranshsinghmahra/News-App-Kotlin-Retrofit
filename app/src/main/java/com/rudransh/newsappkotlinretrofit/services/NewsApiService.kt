package com.rudransh.newsappkotlinretrofit.services

import com.rudransh.newsappkotlinretrofit.models.NewsModel
import retrofit2.Call
import retrofit2.http.Query
import retrofit2.http.GET

interface NewsApiService {
    @GET("everything")
    fun getNews(
        @Query("domains") domains: String,
        @Query("apiKey") apiKey: String
    ): Call<NewsModel>
}