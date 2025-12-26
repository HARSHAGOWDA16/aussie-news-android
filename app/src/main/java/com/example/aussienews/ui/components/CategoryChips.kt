package com.example.aussienews.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.aussienews.ui.model.NewsCategory

@Composable
fun CategoryChips(
    selectedCategory: NewsCategory,
    onCategorySelected: (NewsCategory) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        NewsCategory.values().forEach { category ->
            FilterChip(
                selected = category == selectedCategory,
                onClick = { onCategorySelected(category) },
                label = { Text(category.name.lowercase().replaceFirstChar { it.uppercase() }) }
            )
        }
    }
}
