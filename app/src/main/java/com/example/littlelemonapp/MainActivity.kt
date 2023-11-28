package com.example.littlelemonapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.littlelemonapp.ui.theme.LittleLemonAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // shared Preference
                    val sharedPref = getSharedPreferences(
                        getString(R.string.preference_file),
                        Context.MODE_PRIVATE
                    )
                    val navController = rememberNavController()
                    Navigation(navController = navController, sharedPreferences = sharedPref, context = LocalContext.current)
                }
            }
        }
    }
}