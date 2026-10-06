package com.example.medicineretail.view.navigation

import kotlinx.serialization.Serializable

sealed class Routes {

    @Serializable
    object LoginScreen : Routes()

    @Serializable
    object SignupScreen : Routes()

    @Serializable
    object  WaitingScreen : Routes()


    @Serializable
    data object HomeScreen : Routes()

    @Serializable
    data object CategoriesScreen : Routes()

    @Serializable
    data object OrdersScreen : Routes()

    @Serializable
    data object ProfileScreen : Routes()
}


