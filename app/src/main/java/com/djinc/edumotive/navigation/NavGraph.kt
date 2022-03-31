package com.djinc.edumotive.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.djinc.edumotive.models.ViewModels
import com.djinc.edumotive.screens.*
import com.djinc.edumotive.utils.WindowSize

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NavGraph(navController: NavHostController, windowSize: WindowSize, viewModels: ViewModels) {
    NavHost(navController = navController, startDestination = Screen.Dashboard.route) {
        composable(route = Screen.Dashboard.route) {
            Dashboard(nav = navController, windowSize = windowSize, viewModels = viewModels)
        }
        composable(route = Screen.Parts.route) {
            Parts(nav = navController, windowSize = windowSize, viewModels = viewModels)
        }
        composable(
                route = Screen.Part.route,
                arguments = listOf(navArgument("partId") { type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("partId")?.let {
                PartDetails(
                        partId = it,
                        nav = navController,
                        windowSize = windowSize,
                        viewModels = viewModels
                )
            }
        }
        composable(route = Screen.Exercises.route) {
            Exercises(nav = navController, windowSize = windowSize, viewModels = viewModels)
        }
        composable(
                route = Screen.Exercise.route,
                arguments = listOf(navArgument("exerciseId") { type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("exerciseId")?.let {
                ExerciseDetails(
                        exerciseId = it,
                        nav = navController,
                        windowSize = windowSize,
                        viewModels = viewModels
                )
            }
        }
    }
}
