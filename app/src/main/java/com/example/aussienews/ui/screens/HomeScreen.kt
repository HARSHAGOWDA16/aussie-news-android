package com.example.aussienews.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aussienews.data.model.Article
import com.example.aussienews.ui.components.ArticleCard
import com.example.aussienews.ui.components.CategoryChips
import com.example.aussienews.ui.components.FeaturedArticleCard
import com.example.aussienews.ui.feature.article.ArticleDetailScreen

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    onArticleClick: (Article) -> Unit
) {
    val articles by viewModel.articles.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {

        // 🏷 Category filters
        CategoryChips(
            selectedCategory = selectedCategory,
            onCategorySelected = { viewModel.onCategorySelected(it) }
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when {
                loading -> {
                    CircularProgressIndicator()
                }

                articles.isNotEmpty() -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp)
                    ) {

                        // 🔥 Featured article
                        item {
                            FeaturedArticleCard(
                                article = articles.first(),
                                onClick = { onArticleClick(articles.first()) }
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        // 📰 Remaining articles
                        items(articles.drop(1)) { article ->
                            ArticleCard(
                                article = article,
                                onClick = { onArticleClick(article) }
                            )
                        }
                    }
                }

                else -> {
                    Text("No news available")
                }
            }
        }
    }
}
