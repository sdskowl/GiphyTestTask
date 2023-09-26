package com.giphytesttask.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import com.giphytesttask.ui.screens.gifdetail.GifDetailScreen
import com.giphytesttask.ui.screens.gifdetail.GifDetailScreenNavigation
import com.giphytesttask.ui.screens.home.HomeScreen
import com.giphytesttask.ui.screens.home.HomeScreenNavigation

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController,
        startDestination = Screen.Home.route,
    ) {
        composable(Screen.Home.route) {
            val navigation = HomeScreenNavigation(onDetail = { url ->
                navController.navigate("${Screen.GifDetail.routeWithArg}$url") {
                    popUpTo(Screen.Home.route) {
                        saveState = true
                    }
                    restoreState = true
                }
            })
            HomeScreen(vm = hiltViewModel(), navigation = navigation)
        }
        dialog(
            route = Screen.GifDetail.constRouteWithArg,
            arguments = Screen.GifDetail.arguments
        ) {
            val navigation = GifDetailScreenNavigation(onBack = {
                navController.popBackStack()
            })
            GifDetailScreen(vm = hiltViewModel(), navigation = navigation)
        }
    }
}