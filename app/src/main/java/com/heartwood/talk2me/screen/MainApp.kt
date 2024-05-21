package com.heartwood.talk2me.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.Year

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MainApp() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val topAppBarText by remember { mutableStateOf("Talk2Me") }
    var floatingActionButton by remember { mutableStateOf(true) }

    Scaffold(
        backgroundColor = MaterialTheme.colorScheme.surface,
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = topAppBarText) },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                }
            )
        },
        drawerContent = {
            ModalDrawerSheet(
                drawerShape = RectangleShape
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .weight(1f),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(text = "Menu", modifier = Modifier.padding(16.dp))
                    }
                    Row(
                        modifier = Modifier
                            .weight(1f),
                        horizontalArrangement = Arrangement.End
                    ) {
                        IconButton(onClick = {
                            scope.launch {
                                scaffoldState.drawerState.close()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close"
                            )
                        }
                    }
                }
                Divider(
                    modifier = Modifier.padding(2.dp)
                )
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = null
                        )
                    },
                    label = { Text(text = "Wiadomości") },
                    selected = false,
                    onClick = {
                        scope.launch {
                            navController.navigate(route = "mainscreen")
                            delay(500)
                            scaffoldState.drawerState.close()
                        }
                    }
                )
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null
                        )
                    },
                    label = { Text(text = "Kontakty") },
                    selected = false,
                    onClick = {
                        scope.launch {
                            navController.navigate(route = "contactscreen")
                            delay(500)
                            scaffoldState.drawerState.close()
                        }
                    }
                )
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.List,
                            contentDescription = null
                        )
                    },
                    label = { Text(text = "Archiwum") },
                    selected = false,
                    onClick = {
                        scope.launch {
                            navController.navigate(route = "archivescreen")
                            delay(500)
                            scaffoldState.drawerState.close()
                        }
                    }
                )
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.AccountBox,
                            contentDescription = null
                        )
                    },
                    label = { Text(text = "Konto") },
                    selected = false,
                    onClick = {
                        scope.launch {
                            navController.navigate(route = "accountscreen")
                            delay(500)
                            scaffoldState.drawerState.close()
                        }
                    }
                )
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = null
                        )
                    },
                    label = { Text(text = "Wyloguj") },
                    selected = false,
                    onClick = {
                        scope.launch {
                            navController.navigate(route = "logoutscreen")
                            delay(500)
                            scaffoldState.drawerState.close()
                        }
                    }
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(bottom = 2.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = '\u00AE' + "Talk2Me " + Year.now(),
                        fontSize = 12.sp
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "mainscreen",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("mainscreen") {
                MainScreen(
                    clickOnChat = { nameFriend: String ->
                        navController.navigate(route = "chatscreen/$nameFriend")
                    }
                )
            }
            composable("contactscreen") {
                ContactsScreen(
                )
            }
            composable("accountscreen") {
                AccountScreen(
                )
            }
            composable("archivescreen") {
                ArchiveScreen(
                )
            }
            composable(route = "chatscreen/{nameFriend}") { backStackEntry ->
                val nameFriend = backStackEntry.arguments?.getString("nameFriend")
                ChatScreen(
                    nameFriend ?: ""
                )
                floatingActionButton = false
            }
            composable("logoutscreen") {
                LogOutScreen(
                )
            }
        }
    }
}
