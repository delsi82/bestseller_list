package com.delsi.bestsellerlist.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.delsi.bestsellerlist.R
import com.delsi.bestsellerlist.ui.screens.Home
import com.delsi.bestsellerlist.ui.screens.Message
import com.delsi.bestsellerlist.ui.screens.MessageCard
import com.delsi.bestsellerlist.ui.screens.SplashScreen
import com.delsi.testcompose.ui.theme.BestsellerListTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BestsellerListTheme {
                val navigation = rememberNavController()
                initNavigation(navController = navigation)
            }
        }
    }

    @Composable
    private fun initNavigation(navController: NavHostController) {
        NavHost(navController = navController, startDestination = "splashScreen") {
            composable("home") {
                Home(navController = navController)
            }
            composable("splashScreen") {
                SplashScreen(navController = navController)
            }
        }
    }

}