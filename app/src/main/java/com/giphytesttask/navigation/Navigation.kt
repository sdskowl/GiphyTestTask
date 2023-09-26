package com.giphytesttask.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object GifDetail : Screen("gifDetail") {
        const val gifid = "gifUrl"
        val constRouteWithArg = "$route?$gifid={$gifid}"
        val routeWithArg = "$route?$gifid="
        val arguments = listOf(
            navArgument(gifid) {
                type = NavType.StringType
                defaultValue = ""
            }
        )
    }
}