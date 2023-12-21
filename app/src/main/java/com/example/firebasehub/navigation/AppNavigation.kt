package com.example.firebasehub.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firebasehub.screens.HomeScreen
import com.example.firebasehub.screens.LoginScreen
import com.example.firebasehub.screens.SignupScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()





    NavHost(navController = navController, startDestination = Screen.SignupScreen.route) {

        composable(route = Screen.SignupScreen.route) {
            SignupScreen(navController)
        }

        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController)
        }

        composable(route=Screen.HomeScreen.route){
            HomeScreen()
        }
    }
}