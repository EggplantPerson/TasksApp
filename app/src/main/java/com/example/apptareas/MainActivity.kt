package com.example.apptareas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apptareas.ui.theme.AppTareasTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTareasTheme {
                Navegador()
            }
        }
    }
}

@Composable
fun Navegador () {

    val navController = rememberNavController()

    // Contenedor donde registramos las pantallas
    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {

        composable(
            route = Routes.LOGIN
        ) {
            LoginScreen(
                navController = navController
            )
        }

        composable(
            route = Routes.TASKS
        ) {
            ListTasksScreen(
                onRegresar = {
                    navController.popBackStack()
                }
            )
        }

    }

}
object Routes {
    const val LOGIN = "login"
    const val TASKS = "tasks"
}