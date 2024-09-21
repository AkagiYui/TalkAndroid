package com.akagiyui.talk

/**
 * 屏幕
 */
sealed class Screens(val route: String) {
    data object Home : Screens("home_route")
    data object Search : Screens("search_route")
    data object Profile : Screens("profile_route")
}
