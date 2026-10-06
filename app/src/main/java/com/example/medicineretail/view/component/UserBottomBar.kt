 package com.example.medicineretail.view.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.medicineretail.view.navigation.Routes

sealed class UserBottomScreen(
    val route: Routes,
    val title: String,
    val icon: ImageVector
) {

    data object Home : UserBottomScreen(
        Routes.HomeScreen,
        "Home",
        Icons.Default.Home
    )

    data object Categories : UserBottomScreen(
        Routes.CategoriesScreen,
        "Categories",
        Icons.Default.Category
    )

    data object Orders : UserBottomScreen(
        Routes.OrdersScreen,
        "Orders",
        Icons.Default.ReceiptLong
    )

    data object Profile : UserBottomScreen(
        Routes.ProfileScreen,
        "Profile",
        Icons.Default.Person
    )
}

@Composable
fun UserBottomBar(
    navController: NavController,
    currentRoute: Routes?
) {

    val items = listOf(
        UserBottomScreen.Home,
        UserBottomScreen.Categories,
        UserBottomScreen.Orders,
        UserBottomScreen.Profile
    )

    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {

        items.forEach { screen ->

            val isSelected = currentRoute == screen.route

            NavigationBarItem(

                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.title,
                        modifier = Modifier.size(22.dp)
                    )
                },

                label = {
                    Text(
                        text = screen.title,
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) {
                            FontWeight.Bold
                        } else {
                            FontWeight.Medium
                        }
                    )
                },

                selected = isSelected,

                onClick = {

                    if (currentRoute != screen.route) {

                        navController.navigate(screen.route) {

                            popUpTo(
                                navController.graph.findStartDestination().id
                            ) {
                                saveState = true
                            }

                            launchSingleTop = true

                            restoreState = true
                        }
                    }
                },

                colors = NavigationBarItemDefaults.colors(

                    selectedIconColor = Color(0xFF1479F2),
                    selectedTextColor = Color(0xFF1479F2),

                    unselectedIconColor = Color(0xFF8A9FB8),
                    unselectedTextColor = Color(0xFF8A9FB8),

                    indicatorColor = Color(0xFFE2EEFE)
                )
            )
        }
    }
}
