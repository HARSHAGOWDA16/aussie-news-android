package com.example.aussienews.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aussienews.data.model.Article
import com.example.aussienews.ui.components.ArticleCard
import com.example.aussienews.ui.components.CategoryChips
import com.example.aussienews.ui.components.FeaturedArticleCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    onArticleClick: (Article) -> Unit,
    onSearchClick: () -> Unit = {} // future use
) {
    val articles by viewModel.articles.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("AussieNews") },
                actions = {
                    IconButton(onClick = onSearchClick) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

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
}
