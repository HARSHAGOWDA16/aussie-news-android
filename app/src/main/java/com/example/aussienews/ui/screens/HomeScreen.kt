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

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel()
) {
    val articles by viewModel.articles.collectAsState()
    val loading by viewModel.loading.collectAsState()
    var selectedArticle by remember { mutableStateOf<Article?>(null) }


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            selectedArticle != null -> {
                ArticleDetailScreen(
                    article = selectedArticle!!,
                    onBack = { selectedArticle = null }
                )
            }

            loading -> {
                CircularProgressIndicator()
            }

            articles.isNotEmpty() -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(articles) { article ->
                        ArticleCard(
                            article = article,
                            onClick = { selectedArticle = it }
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