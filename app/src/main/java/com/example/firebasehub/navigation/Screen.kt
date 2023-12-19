package com.example.firebasehub.navigation

sealed class Screen(val route:String) {
    object LoginScreen: Screen("login_screen")
    object SignupScreen: Screen("signup_screen")
}