package com.example.aussienews.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.aussienews.data.model.Article
import com.example.aussienews.ui.auth.AuthViewModel
import com.example.aussienews.ui.auth.LoginScreen
import com.example.aussienews.ui.feature.article.ArticleDetailScreen
import com.example.aussienews.ui.screens.ExploreScreen
import com.example.aussienews.ui.screens.HomeScreen
import com.example.aussienews.ui.screens.ProfileScreen
import com.example.aussienews.ui.screens.SearchScreen

@Composable
fun AussieNewsApp(
    authViewModel: AuthViewModel = viewModel()
) {
    val isLoggedIn by authViewModel.isLoggedIn.collectAsState()

    if (!isLoggedIn) {
        LoginScreen(
            onLoginSuccess = { authViewModel.login() }
        )
    } else {
        MainAppContent(
            onLogout = { authViewModel.logout() }
        )
    }
}

@Composable
fun MainAppContent(
    onLogout: () -> Unit
) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Explore,
        BottomNavItem.Settings
    )

    var selectedItem by remember { mutableStateOf<BottomNavItem>(BottomNavItem.Home) }
    var selectedArticle by remember { mutableStateOf<Article?>(null) }

    Scaffold(
        bottomBar = {
            if (selectedArticle == null) { // hide bottom bar on detail screen
                NavigationBar {
                    items.forEach { item ->
                        NavigationBarItem(
                            selected = selectedItem == item,
                            onClick = { selectedItem = item },
                            icon = {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.title
                                )
                            },
                            label = { Text(item.title) }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            // ✅ ARTICLE DETAIL SCREEN
            selectedArticle?.let { article ->
                ArticleDetailScreen(
                    article = article,
                    onBack = { selectedArticle = null }
                )
                return@Box
            }

            // ✅ NORMAL BOTTOM NAV SCREENS
            when (selectedItem) {
                BottomNavItem.Home ->
                    HomeScreen(
                        onArticleClick = { article ->
                            selectedArticle = article
                        }
                    )

                BottomNavItem.Explore -> ExploreScreen()
                BottomNavItem.Settings -> ProfileScreen(onLogout)
            }
        }
    }
}
