package com.example.fetchcodingexercise.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fetchcodingexercise.ui.viewmodel.ItemListViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemListScreen(contentPaddingValues: PaddingValues) {

    val viewModel = hiltViewModel<ItemListViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getItems()
    }

    Box(modifier = Modifier.padding(contentPaddingValues)) {
        LazyColumn(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            state.groupOfItems.forEach { entry ->
                // Header denoting group by listId
                stickyHeader {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Brush.linearGradient(
                                    listOf(
                                        Color.White,
                                        Color.Transparent
                                    )
                                )
                            ),
                    ) {
                        val header = "Group ${entry.key}"
                        Text(
                            header,
                            modifier = Modifier
                                .padding(8.dp),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                items(entry.value) { item ->
                    val name = item.name ?: return@items
                    Text(
                        name,
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
        }
    }
}