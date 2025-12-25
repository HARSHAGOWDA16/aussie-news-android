package com.example.aussienews.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.aussienews.data.model.Article
import com.example.aussienews.ui.feature.article.ArticleDetailScreen
import com.example.aussienews.ui.screens.HomeScreen

@Composable
fun HomeNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                onArticleClick = { article ->
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("article", article)

                    navController.navigate("detail")
                }
            )
        }

        composable("detail") {
            val article =
                navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.get<Article>("article")

            article?.let {
                ArticleDetailScreen(
                    article = it,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}
