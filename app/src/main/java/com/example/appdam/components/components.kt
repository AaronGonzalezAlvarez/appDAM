package com.example.appdam.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddAlarm
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.LocalActivity
import androidx.compose.material.icons.filled.SouthAmerica
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.appdam.R
import com.example.appdam.nav.Routes
import com.example.appdam.viewModels.MenuViewModel


@Composable
fun ListDraw(viewModel: MenuViewModel, navController: NavHostController) {
    val list : List<ListActionDraw> = listOf(
        ListActionDraw("Menú principal", Icons.Filled.AddAlarm){
            navController.navigate(route = Routes.ScreenMenu.route)
        },
        ListActionDraw("Mis actividades", Icons.Filled.LocalActivity){
            navController.navigate(route = Routes.ScreenUserInscription.route)
        },
        ListActionDraw("Actividades de hoy", Icons.Filled.SouthAmerica){
            navController.navigate(route = Routes.ScreenUserInscriptionToday.route)
        },
        ListActionDraw("Cerrar sesión", Icons.Filled.AccountCircle){
            viewModel.closeSession()
            navController.navigate(route = Routes.ScreenLogin.route)
        }
    )
    ModalDrawerSheet {
        Column(
            modifier = Modifier.padding(top=10.dp)
        ) {
            for (x in list){
                ListItem(
                    headlineContent = { Text(x.text) },
                    leadingContent = {
                        Icon(
                            x.icon,
                            contentDescription = "Localized description",tint = colorResource(id = R.color.icon)
                        )
                    },
                    trailingContent = {
                        IconButton(onClick = {
                            //viewModel.showDialog(x.textDialog)
                            x.action()
                        }) {
                            Icon(
                                imageVector = Icons.Filled.AddCircle,
                                contentDescription = "Settings",
                                tint = colorResource(id = R.color.icon
                                )
                            )
                        }
                    }
                )
            }
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar( /*menuUiState: MenuUiState*/) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Bienvenido"
                )
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )

    )
}