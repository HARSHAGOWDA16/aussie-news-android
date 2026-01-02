package com.example.aussienews.ui

import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

enum class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val route: String
) {
    Home("Home", Icons.Filled.Home, "home"),
    Explore("Explore", Icons.Filled.Star, "explore"),
    Settings("Settings", Icons.Filled.Settings, "setting")
}
