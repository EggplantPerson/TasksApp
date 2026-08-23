package com.example.apptareas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Task(
    val id: Int,
    val title: String,
    val description: String,
    val status: String
)

val dummyTasks = listOf(
    Task(1, "Comprar despensa", "Ir a Soriana por pan, jamón y leche", "Pendiente"),
    Task(2, "Estudiar Kotlin", "Repasar Jetpack Compose y navegación", "Pendiente"),
    Task(3, "Lavar el carro", "Lavado completo incluyendo interiores", "Terminado"),
    Task(4, "Pagar el internet", "Pagar antes de que se corte el servicio", "Pendiente"),
    Task(5, "Hacer ejercicio", "Correr 30 minutos", "Terminado"),
    Task(6, "Leer un libro", "Avanzar al menos 2 capítulos", "Pendiente"),
    Task(7, "Limpiar la casa", "Barrer y trapear", "Terminado"),
    Task(8, "Molestar amigos", "Mandar 30 reels al grupo para que nadie los vea", "Pendiente"),
    Task(9, "Comprar comida para gatas", "Comprar marca Nupec en mundo animal", "Terminado"),
    Task(10, "Hacer tareas", "Terminar tarea de Aplicaciones Móviles", "Terminado"),
    Task(11, "Buscar trabajo", "Revisar computrabajo", "Pendiente"),
    Task(12, "Cocinar la cena", "Preparar algo para cenar", "Terminado"),
    Task(13, "Depositar efectivo", "Pasar efectivo a la tarjeta de débito", "Pendiente"),
    Task(14, "Arreglar reciclaje", "Aplastar cajas y botellas para reciclar", "Pendiente"),
    Task(15, "Organizar el clóset", "Sacar ropa vieja", "Terminado"),
    Task(16, "Backup de archivos", "Respaldar documentos importantes en la nube", "Pendiente"),
    Task(17, "Arreglar el patio", "Arrancar hierbas malas", "Terminado"),
    Task(18, "Comprar agua", "Ir a comprar garrafones", "Pendiente"),
    Task(19, "Revisar correos", "Leer los correos recientes", "Pendiente"),
    Task(20, "Renovar la licencia", "Tramitar la renovación de la licencia", "Terminado")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListTasksScreen(onRegresar: () -> Unit) {

    val tasks = remember { dummyTasks.toMutableStateList() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Mis tareas", fontWeight = FontWeight.SemiBold)
                },
                navigationIcon = {
                    IconButton(onClick = onRegresar) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Regresar al login"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = SuperficieOscura,
                    titleContentColor = TextoPrimario,
                    navigationIconContentColor = TextoPrimario
                )
            )
        },
        containerColor = FondoOscuro
    ) { innerPadding ->

        if (tasks.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Oops, te quedaste sin tareas",
                    fontSize = 18.sp,
                    color = TextoSecundario,
                    fontWeight = FontWeight.Medium
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(items = tasks, key = { it.id }) { t ->
                    TaskCard(
                        task = t,
                        onDelete = {
                            tasks.remove(t)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun TaskCard(
    task: Task,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = SuperficieOscura)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = task.title,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextoPrimario
                )

                Text(
                    text = task.description,
                    fontSize = 14.sp,
                    color = TextoSecundario,
                    modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
                )

                StatusCheck(status = task.status)
            }

            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Eliminar tarea",
                    tint = Color(0xFFFF6B6B)
                )
            }
        }
    }
}

@Composable
fun StatusCheck(status: String) {
    val esTerminado = status == "Terminado"
    val fondo = if (esTerminado) Color(0xFF1B3A4B) else Color(0xFF2E2E2E)
    val texto = if (esTerminado) Color(0xFF4DABF7) else TextoSecundario
    val icono = if (esTerminado) Icons.Filled.Check else Icons.Filled.Clear

    Box(
        modifier = Modifier
            .background(color = fondo, shape = RoundedCornerShape(50))
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icono,
                contentDescription = null,
                tint = texto,
                modifier = Modifier.size(14.dp)
            )
            Text(
                text = status,
                color = texto,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}