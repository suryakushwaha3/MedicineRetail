 package com.example.medicineretail.navigation

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

import com.example.medicineretail.view.auth.LoginScreen
import com.example.medicineretail.view.auth.SignupScreen
import com.example.medicineretail.view.auth.WaitingScreen

import com.example.medicineretail.view.component.UserBottomBar

import com.example.medicineretail.view.CotegryScreen.CategoriesScreen
import com.example.medicineretail.view.Screen.HomeScreen
import com.example.medicineretail.view.Screen.OrderScreen
import com.example.medicineretail.view.profile.ProfileScreen

import com.example.medicineretail.view.navigation.Routes
import com.example.medicineretail.viewModel.AuthViewModel


@Composable
fun NavApp(
    innerPadding: PaddingValues
) {

    val navController = rememberNavController()

    val authViewModel: AuthViewModel = hiltViewModel()

    val context = LocalContext.current

    val isLoggedIn = remember {

        context
            .getSharedPreferences(
                "auth",
                Context.MODE_PRIVATE
            )
            .getBoolean(
                "isLoggedIn",
                false
            )
    }


    val startDestination = if (isLoggedIn) {

        Routes.HomeScreen

    } else {

        Routes.LoginScreen
    }



    val backStackEntry by
    navController.currentBackStackEntryAsState()

    val currentRoute = backStackEntry
        ?.destination
        ?.route




    val showBottomBar =
        currentRoute?.contains("HomeScreen") == true ||
                currentRoute?.contains("CategoriesScreen") == true ||
                currentRoute?.contains("OrdersScreen") == true ||
                currentRoute?.contains("ProfileScreen") == true



    Scaffold(

        bottomBar = {

            if (showBottomBar) {

                UserBottomBar(
                    navController = navController,

                    currentRoute =
                        when {

                            currentRoute?.contains(
                                "CategoriesScreen"
                            ) == true ->
                                Routes.CategoriesScreen

                            currentRoute?.contains(
                                "OrdersScreen"
                            ) == true ->
                                Routes.OrdersScreen

                            currentRoute?.contains(
                                "ProfileScreen"
                            ) == true ->
                                Routes.ProfileScreen

                            else ->
                                Routes.HomeScreen
                        }
                )
            }
        }

    ) { scaffoldPadding ->



        NavHost(

            navController = navController,

            startDestination = startDestination,

            modifier = Modifier.padding(
                scaffoldPadding
            )
        ) {



            composable<Routes.LoginScreen> {

                LoginScreen(
                    navController = navController,
                    viewModel = authViewModel
                )
            }



            composable<Routes.SignupScreen> {

                SignupScreen(
                    navController = navController,
                    viewModel = authViewModel
                )
            }


            composable<Routes.WaitingScreen> {

                WaitingScreen(
                    navController = navController
                )
            }



            composable<Routes.HomeScreen> {

                HomeScreen(
                    navController = navController
                )
            }



            composable<Routes.CategoriesScreen> {

                CategoriesScreen()
            }




            composable<Routes.OrdersScreen> {

                OrderScreen()
            }





            composable<Routes.ProfileScreen> {

                ProfileScreen()
            }
        }
    }
}
