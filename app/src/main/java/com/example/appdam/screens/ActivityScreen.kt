package com.example.appdam.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ContentPasteSearch
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.appdam.R
import com.example.appdam.components.ListDraw
import com.example.appdam.components.TopBar
import com.example.appdam.retrofit.model.activity.delete.IdActivity
import com.example.appdam.retrofit.model.activity.oneActivity.OneActivity
import com.example.appdam.retrofit.model.activity.oneActivity.User
import com.example.appdam.uiState.ActivityUiState
import com.example.appdam.viewModels.ActivityViewModel
import com.example.appdam.viewModels.MenuViewModel
import com.google.gson.Gson
import kotlinx.coroutines.launch

@Composable
fun ActivityScreen(
    viewModel: ActivityViewModel = viewModel(),
    viewModelMenu: MenuViewModel = viewModel(),
    modifier: Modifier = Modifier,
    navController: NavHostController,
    idActivity: String
) {
    val dataUiState by viewModel.uiState.collectAsState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    LaunchedEffect(idActivity) {
        viewModel.loadActivity(idActivity)
    }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ListDraw(viewModelMenu, navController)
        },
    ) {
        Scaffold(
            topBar = {
                TopBar()
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ContentPasteSearch,
                        contentDescription = "opciones",
                        tint = colorResource(id = R.color.icon)
                    )
                }


            },
            floatingActionButtonPosition = FabPosition.End,
            bottomBar = {
                BottomAppBar(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "",
                    )
                }
            }


        ) {

                innerPadding ->
            ActivityBody(
                innerPadding = innerPadding,
                navController,
                dataUiState,
                viewModel
            )

        }
    }

}

/**
 * Method that contains most of the important usages of the App
 */

@Composable
fun ActivityBody(
    innerPadding: PaddingValues,
    navController: NavHostController,
    dataUiState: ActivityUiState,
    viewModel: ActivityViewModel
) {
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier.padding(innerPadding)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 25.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 10.dp).verticalScroll(scrollState)
            ) {
                val gson = Gson()
                val oneActivity = gson.fromJson(dataUiState.activity, OneActivity::class.java)
                oneActivity?.let { it ->
                    Card(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 5.dp, vertical = 10.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                modifier = Modifier.padding(vertical = 10.dp),
                                text = it.name
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(25.dp)
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp),
                            Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(text = it.date)
                                Text(text = it.startTime)
                                Text(text = it.endTime)
                            }
                            Column {
                                Text(text = it.zone)
                                Text(text = it.creator.nick)
                                Text(text = "Total ${it.users.size}")
                            }
                        }
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(25.dp)
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp, horizontal = 5.dp)
                        ) {
                            Text(
                                textAlign = TextAlign.Center,
                                text = it.summary
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(25.dp)
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp, horizontal = 5.dp)
                        ) {
                            Text(text = it.description)
                        }

                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(25.dp)
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp, horizontal = 5.dp)
                        ) {
                            if (it.users.size == 0) {
                                ListItem(
                                    headlineContent = { Text("Sin usuarios en la actividad") }
                                )
                            } else {
                                for (x: User in it.users) {
                                    ListItem(
                                        headlineContent = { Text(text = x.nick) },
                                        leadingContent = {
                                            Icon(
                                                Icons.Filled.Add,
                                                contentDescription = "Corazon icon",
                                                tint = colorResource(id = R.color.icon)
                                            )
                                        }
                                    )
                                }
                            }
                        }



                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            Arrangement.SpaceAround
                        ) {

                            if (it.date >= dataUiState.today) {
                                var auxBol = true
                                for (x in dataUiState.inscription!!) {
                                    if (x.id == it.id) {
                                        ElevatedButton(
                                            colors= ButtonDefaults.elevatedButtonColors(containerColor = colorResource(id = R.color.btn)),
                                            onClick = { viewModel.deleteInscription(it.id) }
                                        ) {
                                            Text("Quitar inscripcion")
                                        }
                                        auxBol = false
                                        break
                                    }
                                }
                                if (auxBol) {
                                    ElevatedButton(
                                        colors= ButtonDefaults.elevatedButtonColors(containerColor = colorResource(id = R.color.btn)),
                                        onClick = { viewModel.addInscription(it.id) }
                                    ) {
                                        Text("Inscribirme")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}