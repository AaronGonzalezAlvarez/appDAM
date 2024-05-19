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
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentPasteSearch
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.appdam.viewModels.MenuViewModel
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appdam.R
import com.example.appdam.components.ListDraw
import com.example.appdam.components.TopBar
import com.example.appdam.uiState.MenuUiState


@Composable
fun MenuScreen(
    viewModel: MenuViewModel = viewModel(),
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val dataUiState by viewModel.uiState.collectAsState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    if(dataUiState.dialog){
        /*DialogListDraw(
            infoDialog= dataUiState.InfoDialog,
            actionItemList = {viewModel.closedDialog()},
            showIndicator = dataUiState.checkProgresCircular,
            TextOrList= dataUiState.TextOrList,
            exit = {viewModel.changeCheckProgresCircular()}
        )*/
    }

    /* Top border */
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ListDraw(viewModel,navController)
        },
    ) {
        Scaffold(
            topBar = {
                TopBar(
                    //menuUiState = dataUiState
                )
            },
            /* Boton flotante */
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        //viewModel.closeSession(navController)
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
                        text = "Actividades para hoy: ${dataUiState.totalActivityNow}",
                    )
                }
            }


        ) {
            /* Principal content */

                innerPadding ->
            MenuBody(
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
fun MenuBody(
    innerPadding: PaddingValues,
    navController: NavHostController,
    dataUiState: MenuUiState,
    viewModel: MenuViewModel
) {
    Box(
        modifier = Modifier.padding(innerPadding)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 25.dp),
            contentAlignment= Alignment.TopCenter
        ){
            /*Column (
                modifier = Modifier
                    .padding(5.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ){*/
            dataUiState.activityTotal?.let { activity ->
                LazyColumn(
                    modifier=Modifier.padding(horizontal = 10.dp)
                ) {
                    items(activity) { item ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(350.dp)
                                .padding(horizontal = 5.dp, vertical = 10.dp)
                        ) {
                            Row (
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement= Arrangement.Center
                            ){
                                Text(
                                    modifier = Modifier.padding(vertical = 10.dp),
                                    text = item.name
                                )
                            }
                            Spacer(modifier = Modifier
                                .fillMaxWidth()
                                .height(25.dp)
                            )
                            Row (
                                modifier = Modifier.fillMaxWidth().padding(5.dp),
                                Arrangement.SpaceBetween
                            ){
                                Column {
                                    Text(text = item.date)
                                    Text(text = item.startTime)
                                    Text(text = item.endTime)
                                }
                                Column {
                                    Text(text = item.zone)
                                    Text(text = item.creator.nick)
                                    Text(text = "Total ${item.users.size}")
                                }
                            }
                            Spacer(modifier = Modifier
                                .fillMaxWidth()
                                .height(25.dp)
                            )
                            Row (
                                modifier=Modifier.fillMaxWidth().padding(vertical = 6.dp, horizontal = 5.dp)
                            ){
                                Text(text = item.description)
                            }
                            Spacer(modifier = Modifier
                                .fillMaxWidth()
                                .height(25.dp)
                            )

                            Row (
                                modifier = Modifier.fillMaxWidth(),
                                Arrangement.SpaceAround
                            ){

                                if(item.date >= dataUiState.today){
                                    var auxBol = true
                                    for (x in dataUiState.inscription!!){
                                        if(x.id ==item.id){
                                            ElevatedButton(
                                                colors= ButtonDefaults.elevatedButtonColors(containerColor = colorResource(id = R.color.btn)),
                                                onClick = { viewModel.deleteInscription(item.id) }) {
                                                Text("Quitar inscripcion")
                                            }
                                            auxBol=false
                                            break
                                        }
                                    }
                                    if(auxBol){
                                        ElevatedButton(
                                            colors= ButtonDefaults.elevatedButtonColors(containerColor = colorResource(id = R.color.btn)),
                                            onClick = { viewModel.addInscription(item.id) }) {
                                            Text("Inscribirme")
                                        }
                                    }
                                }

                                ElevatedButton(
                                    colors= ButtonDefaults.elevatedButtonColors(containerColor = colorResource(id = R.color.btn)),
                                    onClick = { viewModel.info(item.id,navController) }) {
                                    Text("Mas informacióm")
                                }
                            }
                        }
                    }
                }
            }
            //}
        }
    }
}