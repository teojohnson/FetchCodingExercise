package com.example.fetchcodingexercise.data

import com.example.fetchcodingexercise.data.models.Item
import retrofit2.Response
import retrofit2.http.GET

interface ItemListService {
    @GET("hiring.json")
    suspend fun getItemList(): Response<List<Item>>
}