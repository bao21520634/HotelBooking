package com.example.doanmobileui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.doanmobileui.ui.theme.DoANMobileUITheme
import LoginScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import SignUpScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
                MainApp()
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()
    DoANMobileUITheme {
        NavHost(navController = navController, startDestination = "logIn") {

            composable("logIn") {
                LoginScreen(openSignUpScreen = {
                    navController.navigate("SignUp")
                })
            }

            composable("SignUp") {
                SignUpScreen( openLoginScreen = {
                    navController.navigate("logIn")
                })
            }
        }

    }
}