package com.example.appdam.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.appdam.retrofit.objects.ActivityRetrofit
import com.example.appdam.screens.ActivityScreen
import com.example.appdam.screens.LoginScreen
import com.example.appdam.screens.MenuScreen
import com.example.appdam.screens.UserInscriptionScreen
import com.example.appdam.screens.UserInscriptionTodayScreen
import kotlinx.coroutines.launch

@Composable
fun AppNav(navController: NavHostController = rememberNavController()) {
    /* Starts with Login Screen */
    val coroutineScope = rememberCoroutineScope()
    NavHost(
        navController = navController,
        startDestination = Routes.ScreenLogin.route
    ) {
        composable(
            route = Routes.ScreenLogin.route
        ) {
            LoginScreen(navController = navController)
        }
        composable(
            route = Routes.ScreenMenu.route
        ) {
            MenuScreen(navController = navController)
        }

        composable(
            route = Routes.ScreenUserInscription.route
        ) {
            UserInscriptionScreen(navController = navController)
        }

        composable(
            route = Routes.ScreenUserInscriptionToday.route
        ) {
            UserInscriptionTodayScreen(navController = navController)
        }

        composable(
            route = Routes.ScreenActivity.route + "/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            if (id != null) {
                ActivityScreen(navController = navController, idActivity = id)
            }
        }
    }
}