package com.example.aussienews.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.example.aussienews.ui.auth.LoginScreen
import com.example.aussienews.ui.screens.*

@Composable
fun AussieNewsApp() {
    var isLoggedIn by remember { mutableStateOf(false) }

    if (!isLoggedIn) {
        LoginScreen(
            onLoginSuccess = { isLoggedIn = true }
        )
    } else {
        MainAppContent(
            onLogout = { isLoggedIn = false }
        )
    }
}
@Composable
    fun MainAppContent(
        onLogout: () -> Unit
)     {
        val items = listOf(
            BottomNavItem.Home,
            BottomNavItem.Search,

            BottomNavItem.Profile
        )

        var selectedItem by remember {
            mutableStateOf<BottomNavItem>(BottomNavItem.Home)
        }

        Scaffold(
            bottomBar = {
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
        ) { innerPadding ->

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                when (selectedItem) {
                    BottomNavItem.Home -> HomeScreen()
                    BottomNavItem.Search -> SearchScreen()

                    BottomNavItem.Profile -> ProfileScreen(onLogout)
                }
            }
        }
    }