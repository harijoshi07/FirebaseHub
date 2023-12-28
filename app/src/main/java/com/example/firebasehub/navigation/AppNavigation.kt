package com.example.firebasehub.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.firebasehub.screens.HomeScreen
import com.example.firebasehub.screens.LoginScreen
import com.example.firebasehub.screens.SignupScreen

@Composable
fun AppNavigation(navController: NavHostController) {

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

//        composable(route=Screen.SignupViewModel.route){
//           Screen.SignupViewModel(navController)
//        }
    }
}