package com.example.fetchcodingexercise.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchcodingexercise.data.ItemListRepository
import com.example.fetchcodingexercise.data.models.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemListViewModel @Inject constructor(private val repository: ItemListRepository) :
    ViewModel() {

    data class State(
        val groupOfItems: Map<Int, List<Item>> = mapOf()
    )

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    fun getItems() {
        viewModelScope.launch {
            val response = repository.getItemList()
            if (response != null) {
                _state.value = state.value.copy(groupOfItems = sortItems(response))
            }
        }
    }

    private fun sortItems(listOfItems: List<Item>?): Map<Int, List<Item>> {
        val listOfItems = listOfItems ?: return emptyMap()
        val nullRemovedList = listOfItems.filter { it.name?.isNotBlank() == true }
        val sortedMap = nullRemovedList.groupBy { it.listId }.toSortedMap().mapValues { entry ->
            entry.value.sortedBy {
                it.id // sorted by "id" to avoid issues with "name" of type string, and assuming that "id" and "name" are equal in non-null items
            }
        }
        return sortedMap
    }
}