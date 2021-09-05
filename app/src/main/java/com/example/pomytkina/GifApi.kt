package com.example.pomytkina

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GifApi {
    @GET("/{section}/{page}?json=true")
    suspend fun getSectionGIFs(
        @Path("section") section: String,
        @Path("page") page: Int,
    ): Response<ResponseWrapper>

    @GET("/random?json=true")
    suspend fun getRandomGif(): Response<Gif>
}