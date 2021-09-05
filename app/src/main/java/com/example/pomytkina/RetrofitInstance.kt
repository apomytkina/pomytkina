package com.example.pomytkina

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: GifApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://developerslife.ru")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GifApi::class.java)
    }
}