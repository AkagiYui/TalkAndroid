package com.akagiyui.talk

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * 底部导航项目
 */
data class BottomNavigationItem(
    val label : String = "",
    val icon : ImageVector = Icons.Filled.Home,
    val route : String = ""
) {
    companion object {
        /**
         * 底部导航项目信息
         */
        fun bottomNavigationItems() : List<BottomNavigationItem> {
            return listOf(
                BottomNavigationItem(
                    label = "Home",
                    icon = Icons.Filled.Home,
                    route = Screens.Home.route
                ),
                BottomNavigationItem(
                    label = "Search",
                    icon = Icons.Filled.Search,
                    route = Screens.Search.route
                ),
                BottomNavigationItem(
                    label = "Profile",
                    icon = Icons.Filled.AccountCircle,
                    route = Screens.Profile.route
                ),
            )
        }
    }


}
