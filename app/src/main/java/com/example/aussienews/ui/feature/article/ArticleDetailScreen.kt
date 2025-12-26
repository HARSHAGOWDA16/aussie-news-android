package com.example.aussienews.ui.feature.article

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.aussienews.data.model.Article
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailScreen(
    article: Article,
    onBack: () -> Unit
) {
    var isSummarizing by remember { mutableStateOf(false) }
    var aiSummary by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Article") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
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
                .verticalScroll(rememberScrollState())
        ) {

            // 🖼 Article image
            article.imageUrl?.let { imageUrl ->
                AsyncImage(
                    model = imageUrl,
                    contentDescription = article.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(260.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Column(modifier = Modifier.padding(16.dp)) {

                // 📰 Headline
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(modifier = Modifier.height(12.dp))

                // 📝 Description
                article.description?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // 🤖 AI Summary Button
                Button(
                    onClick = {
                        isSummarizing = true
                        aiSummary = null
                    },
                    enabled = !isSummarizing,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("AI Summary")
                }

                // ⏳ Loading
                if (isSummarizing) {
                    Spacer(modifier = Modifier.height(16.dp))
                    CircularProgressIndicator()
                }

                // 🧠 AI Summary Result (mock)
                aiSummary?.let { summary ->
                    Spacer(modifier = Modifier.height(24.dp))
                    Card {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "AI Summary",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = summary)
                        }
                    }
                }

                // 🔁 Simulate AI response
                LaunchedEffect(isSummarizing) {
                    if (isSummarizing) {
                        delay(1500) // simulate API call
                        aiSummary =
                            "• This article discusses a key recent development.\n" +
                                    "• It highlights its impact on Australia.\n" +
                                    "• Experts suggest this may influence upcoming decisions."
                        isSummarizing = false
                    }
                }
            }
        }
    }
}
