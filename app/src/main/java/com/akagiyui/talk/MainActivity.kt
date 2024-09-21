package com.akagiyui.talk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.akagiyui.talk.screen.HomeScreen
import com.akagiyui.talk.screen.ProfileScreen
import com.akagiyui.talk.screen.SearchScreen
import com.akagiyui.talk.ui.theme.TalkTheme

/**
 * 程序入口
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TalkTheme { // 主题
                MainContent() // 主内容
            }
        }
    }
}


/**
 * 主内容
 */
@Composable
fun MainContent() {
    // 选中的导航栏项目
    var navigationSelectedItem by remember {
        mutableIntStateOf(0)
    }

    // 导航控制器
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(navController, navigationSelectedItem) { index ->
                navigationSelectedItem = index
            }
        }
    ) { paddingValues ->
        // 导航宿主，展示目标内容
        NavHost(
            navController = navController,
            startDestination = Screens.Home.route,
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            composable(Screens.Home.route) {
                HomeScreen(
                    navController
                )
            }
            composable(Screens.Search.route) {
                SearchScreen(
                    navController
                )
            }
            composable(Screens.Profile.route) {
                ProfileScreen(
                    navController
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    navController: NavController,
    selectedItem: Int,
    onItemSelected: (Int) -> Unit
) {
    NavigationBar {
        // 遍历所有导航栏项目
        BottomNavigationItem.bottomNavigationItems()
            .forEachIndexed { index, navigationItem ->
                // 创建导航栏项目
                NavigationBarItem(
                    selected = index == selectedItem,
                    label = {
                        Text(navigationItem.label)
                    },
                    icon = {
                        Icon(
                            navigationItem.icon,
                            contentDescription = navigationItem.label
                        )
                    },
                    onClick = {
                        onItemSelected(index)
                        navController.navigate(navigationItem.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true // 弹栈时保存每个目标的状态
                            }
                            launchSingleTop = true // 如果导航目标已在栈顶，不创建新实例，重用现有实例
                            restoreState = true // 如果目标之前被访问过并且状态被保存，恢复之前的状态
                        }
                    }
                )
            }
    }
}
