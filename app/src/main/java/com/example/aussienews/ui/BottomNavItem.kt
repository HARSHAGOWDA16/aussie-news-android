package com.example.aussienews.ui

import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

enum class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val route: String
) {
    Home("Home", Icons.Filled.Home, "home"),
    Search("Search", Icons.Filled.Search, "search"),
    Profile("Profile", Icons.Filled.Person, "profile")
}
