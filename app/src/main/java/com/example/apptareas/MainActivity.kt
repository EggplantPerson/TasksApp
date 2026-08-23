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

    val navControl = rememberNavController()

    // Contenedor donde registramos las pantallas
    NavHost(
        navController = navControl,
        startDestination = Rutas.LOGIN
    ) {

        composable(
            route = Rutas.LOGIN
        ) {
            LoginScreen(
                onIrDetalle = {
                    navControl.navigate(Rutas.TASKS)
                }
            )
        }

        composable(
            route = Rutas.TASKS
        ) {
            ListTasksScreen(
                onRegresar = {
                    navControl.popBackStack()
                }
            )
        }

    }

}

private object Rutas {
    const val LOGIN = "login"
    const val TASKS = "tasks"
}