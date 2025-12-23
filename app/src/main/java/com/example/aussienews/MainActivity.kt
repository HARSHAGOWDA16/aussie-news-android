package com.example.aussienews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.aussienews.ui.AussieNewsApp
import com.example.aussienews.ui.theme.AussieNewsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AussieNewsTheme {
                AussieNewsApp()
            }
        }
    }
}
