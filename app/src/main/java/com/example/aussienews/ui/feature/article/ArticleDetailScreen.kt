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
    onBack: () -> Unit,
    viewModel: ArticleDetailViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val summaryState by viewModel.summaryState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Article") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
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

            article.imageUrl?.let {
                AsyncImage(
                    model = it,
                    contentDescription = article.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(260.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Column(modifier = Modifier.padding(16.dp)) {

                Text(
                    text = article.title,
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(modifier = Modifier.height(12.dp))

                article.description?.let {
                    Text(text = it, style = MaterialTheme.typography.bodyLarge)
                }

                Spacer(modifier = Modifier.height(24.dp))

                // 🤖 AI Summary Button
                Button(
                    onClick = {
                        viewModel.generateSummary(
                            article.title,
                            article.description
                        )
                    }
                ) {
                    Text("Summarize with AI")
                }

                Spacer(modifier = Modifier.height(16.dp))

                when (summaryState) {
                    is SummaryUiState.Loading -> {
                        CircularProgressIndicator()
                    }
                    is SummaryUiState.Success -> {
                        Text(
                            text = (summaryState as SummaryUiState.Success).summary,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    is SummaryUiState.Error -> {
                        Text(
                            text = "Failed to generate summary",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                    else -> {}
                }
            }
        }
    }
}
