package com.example.apptareas

import android.util.Patterns
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController

//Paleta de colores
val FondoOscuro = Color(0xFF121212)
val SuperficieOscura = Color(0xFF1E1E1E)
val AzulAcento = Color(0xFF4DABF7)
val TextoPrimario = Color(0xFFECECEC)
val TextoSecundario = Color(0xFFA0A0A0)
val BordeOscuro = Color(0xFF3A3A3A)

@Composable
fun LoginScreen(navController : NavController) {

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    val EMAIL_ADRESS_PATTERN = java.util.regex.Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    fun validateEmail(text: String): String? {
        return when {
            text.isEmpty() -> "Introduzca un email"
            EMAIL_ADRESS_PATTERN.matcher(text).matches() -> "Introduzca un email válido"
            else -> null
        }
    }

    fun validatePassword(text:String): String? {
        return when {
            text.isEmpty() -> "Introduzca una contraseña"
            text.length < 8 -> "Introduzca una contraseña de al menos 8 carácteres"
            else -> null
        }
    }

    Scaffold(
        containerColor = FondoOscuro
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Task App",
                color = AzulAcento,
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { textoNuevo -> email = textoNuevo },
                label = { Text("Correo electrónico") },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = AzulAcento,
                    unfocusedBorderColor = BordeOscuro,
                    focusedLabelColor = AzulAcento,
                    unfocusedLabelColor = TextoSecundario,
                    cursorColor = AzulAcento,
                    focusedTextColor = TextoPrimario,
                    unfocusedTextColor = TextoPrimario,
                    unfocusedPlaceholderColor = TextoSecundario,
                    focusedPlaceholderColor = TextoSecundario,
                    focusedContainerColor = SuperficieOscura,
                    unfocusedContainerColor = SuperficieOscura
                ),
                isError = emailError != null,
                supportingText = {
                    if (emailError != null) {
                        Text(
                            text = emailError!!,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { textoNuevo -> password = textoNuevo },
                label = { Text("Contraseña") },
                placeholder = { Text("*********") },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = AzulAcento,
                    unfocusedBorderColor = BordeOscuro,
                    focusedLabelColor = AzulAcento,
                    unfocusedLabelColor = TextoSecundario,
                    cursorColor = AzulAcento,
                    focusedTextColor = TextoPrimario,
                    unfocusedTextColor = TextoPrimario,
                    unfocusedPlaceholderColor = TextoSecundario,
                    focusedPlaceholderColor = TextoSecundario,
                    focusedContainerColor = SuperficieOscura,
                    unfocusedContainerColor = SuperficieOscura
                ),
                isError = passwordError != null,
                supportingText = {
                    if (passwordError != null) {
                        Text(
                            text = passwordError!!,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(28.dp))

            Button(
                onClick = {
                    emailError = validateEmail(email)
                    passwordError = validatePassword(password)
                    if (emailError == null && passwordError == null) {
                        navController.navigate(Routes.TASKS)
                    }
                },
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AzulAcento,
                    contentColor = Color(0xFF121212)
                )
            ) {
                Text("Iniciar sesión", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}