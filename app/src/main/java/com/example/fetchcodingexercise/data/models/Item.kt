package com.example.fetchcodingexercise.data.models
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Item(
    @SerialName("id")
    val id: Int,
    @SerialName("listId")
    val listId: Int,
    @SerialName("name")
    val name: String?
)