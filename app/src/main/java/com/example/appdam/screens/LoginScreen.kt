package com.example.appdam.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ControlPointDuplicate
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.appdam.viewModels.LoginViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appdam.R

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel(),
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val dataUiState by viewModel.uiState.collectAsState()


    /* Content */
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.primary) )
    ) {
        /* GIF */
        /*
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
*/
        /* Content */

        Box {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                /* Middle screen */
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .background(Color(255, 255, 255, 216), shape = RoundedCornerShape(25.dp))
                        .padding(15.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Inicio de sesión",
                            fontSize = 32.sp
                        )
                        Spacer(
                            modifier = Modifier
                                .padding(5.dp)
                        )

                        /* Text Fields */
                        Box {
                            Column {
                                OutlinedTextField(
                                    value = dataUiState.username,
                                    trailingIcon = {
                                        Icon(
                                            imageVector = Icons.Default.Person,
                                            contentDescription = "emailIcon",
                                            tint = colorResource(id = R.color.icon)
                                        )
                                                   },
                                    onValueChange = {
                                        viewModel.usernameChange(it)
                                    },
                                    label = { Text(text = "Usuario") },
                                )

                                Spacer(
                                    modifier = Modifier
                                        .padding(6.dp)
                                )

                                OutlinedTextField(
                                    value = dataUiState.password,
                                    onValueChange = { viewModel.passwordChange(it) },
                                    label = { Text("Password") },
                                    singleLine = true,
                                    visualTransformation = if (dataUiState.seePass) VisualTransformation.None else PasswordVisualTransformation(),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                                    trailingIcon = {
                                        IconButton(onClick = {viewModel.showPass()}){
                                            Icon(
                                                imageVector  = dataUiState.iconPass,
                                                "",
                                                tint = colorResource(id = R.color.icon)
                                            )
                                        }
                                    }
                                )

                                Spacer(
                                    modifier = Modifier
                                        .padding(6.dp)
                                )

                                OutlinedTextField(
                                    value = dataUiState.ip,
                                    trailingIcon = {
                                        Icon(
                                            imageVector = Icons.Default.ControlPointDuplicate,
                                            contentDescription = "ip",
                                            tint = colorResource(id = R.color.icon)
                                        )
                                    },
                                    onValueChange = {
                                        viewModel.ipChange(it)
                                    },
                                    label = { Text(text = "IP") },
                                )
                            }
                        }

                    }
                }

                /* Middle buttons */
                Box(
                    modifier = Modifier
                        .padding(5.dp)
                ) {
                    Row {
                        Button(
                            colors= ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.btn)),
                            onClick = {
                                viewModel.saveConnection(navController)
                            }
                        ) {
                            Text(
                                text = "Enviar datos"
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .padding(5.dp)
                        )
                        Button(
                            colors= ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.btn)),
                            onClick = {
                                viewModel.resetData()
                            }
                        ) {
                            Text(text = "Limpiar pantalla")
                        }

                    }
                }

                if (dataUiState.message) {
                    Snackbar(
                        modifier = Modifier.padding(16.dp),
                        action = {
                            Button(
                                colors= ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.btn)),
                                onClick = {
                                    viewModel.menssageFunFalse()
                                }
                            ) {
                                Text("Cerrar")
                            }
                        },
                        content = {
                            Text(dataUiState.text)
                        }
                    )
                }

            }
        }
    }
}