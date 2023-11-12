package com.rudransh.newsappkotlinretrofit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.rudransh.newsappkotlinretrofit.R
import com.rudransh.newsappkotlinretrofit.models.Article

class CustomAdapter(var context: Context, private val articles: List<Article>) :
    RecyclerView.Adapter<CustomAdapter.NewsViewHolder>() {
    inner class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var newsImage: ImageView = view.findViewById(R.id.news_image)
        var decription: TextView = view.findViewById(R.id.news_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        Glide.with(context).load(articles[position].urlToImage).into(holder.newsImage)
        holder.decription.text = articles[position].description
    }
}