package com.rudransh.newsappkotlinretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rudransh.newsappkotlinretrofit.adapter.CustomAdapter
import com.rudransh.newsappkotlinretrofit.services.NewsApiService
import com.rudransh.newsappkotlinretrofit.models.NewsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    companion object {
        const val API_KEY = "a5a815d4c75d4c62841441930f57d3f0"
        const val BASE_URL = "https://newsapi.org/v2/"
        const val TAG = "NEWS TAG"
    }

    private lateinit var adapter: CustomAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        getNewsData()
    }

    private fun getNewsData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(NewsApiService::class.java)

        retrofit.getNews("wsj.com", API_KEY).enqueue(object : Callback<NewsModel> {
            override fun onResponse(
                call: Call<NewsModel>,
                response: Response<NewsModel>
            ) {
                adapter = CustomAdapter(baseContext, response.body()!!.articles)
                recyclerView.adapter = adapter
                if (response.isSuccessful) {
                    Log.i(TAG, response.body().toString())
                }
            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                Log.i(TAG, t.message.toString())
            }

        })
    }
}