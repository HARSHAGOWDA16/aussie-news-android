package com.example.aussienews.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.aussienews.data.model.Article

@Composable
fun ArticleDetailScreen(
    article: Article,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        article.imageUrl?.let { imageUrl ->
            AsyncImage(
                model = imageUrl,
                contentDescription = article.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        Text(
            text = article.title,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        article.description?.let {
            Text(text = it)
        }
    }
}
