package com.example.aussienews.ui.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.aussienews.data.local.SessionManager
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val sessionManager = SessionManager(application)

    val isLoggedIn = sessionManager.isLoggedIn
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    fun login() {
        viewModelScope.launch {
            sessionManager.setLoggedIn(true)
        }
    }

    fun logout() {
        viewModelScope.launch {
            sessionManager.setLoggedIn(false)
        }
    }
}
