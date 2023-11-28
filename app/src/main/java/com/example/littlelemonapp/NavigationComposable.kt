package com.example.littlelemonapp

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.littlelemonapp.destination.Home
import com.example.littlelemonapp.destination.Onboarding
import com.example.littlelemonapp.destination.Profile
import com.example.littlelemonapp.util.EMAIL
import com.example.littlelemonapp.util.EMPTY_STRING

@Composable
fun Navigation(
    navController: NavHostController,
    sharedPreferences: SharedPreferences,
    context: Context,
) {
    val email = sharedPreferences.getString(EMAIL, EMPTY_STRING)
    NavHost(
        navController = navController,
        startDestination = if (email?.isNotBlank() == true) Home.route else Onboarding.route
    ) {
        composable(Home.route) {
            Home()
        }

        composable(Onboarding.route) {
            Onboarding(
                sharedPreferences = sharedPreferences,
                context = context
            ) {
                navController.navigate(Home.route)
            }
        }

        composable(Profile.route) {
            Profile()
        }
    }
}