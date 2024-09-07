package com.example.fetchcodingexercise.data

import com.example.fetchcodingexercise.data.models.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject

class ItemListRepository @Inject constructor(retrofit: Retrofit) {
    private val service = retrofit.create(ItemListService::class.java)

    suspend fun getItemList(): List<Item>? {
        return withContext(Dispatchers.IO) {
            service.getItemList().body()
        }
    }
}