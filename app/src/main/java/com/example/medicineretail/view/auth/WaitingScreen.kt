 package com.example.medicineretail.view.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.medicineretail.view.navigation.Routes
import kotlinx.coroutines.delay

@Composable
fun WaitingScreen(
    navController: NavHostController
) {

    /*
     * Temporary server verification.
     *
     * Yahan baad me AuthViewModel ki actual
     * server verification call connect karni hai.
     */

    LaunchedEffect(Unit) {

        // Server response ka wait
        delay(2000)

        // Server verification successful
        navController.navigate(Routes.HomeScreen) {

            popUpTo(Routes.WaitingScreen) {
                inclusive = true
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        CircularProgressIndicator()

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Text(
            text = "Waiting for server response...",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
