package com.mgalexandrescu.cashflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mgalexandrescu.cashflow.ui.theme.CashFlowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CashFlowTheme {
                val navController = rememberNavController()
                val backStackEntry = navController.currentBackStackEntryAsState()
                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            NavigationRailItem(
                                selected = backStackEntry.value?.destination?.route == "expenses",
                                onClick = { navController.navigate("expenses") },
                                label= { Text("Expenses") },
                                icon = { Icon(
                                    painterResource(id = R.drawable.spendingsicon),
                                    contentDescription = "spendings"
                                ) })
                            NavigationRailItem(
                                selected = backStackEntry.value?.destination?.route == "reports",
                                onClick = { navController.navigate("reports") },
                                label= { Text("Reports") },
                                icon = { Icon(
                                    painterResource(id = R.drawable.reportsicon),
                                    contentDescription = "reports"
                                ) })
                            NavigationRailItem(
                                selected = backStackEntry.value?.destination?.route == "add",
                                onClick = { navController.navigate("add") },
                                label= { Text("Add") },
                                icon = { Icon(
                                    painterResource(id = R.drawable.addicon),
                                    contentDescription = "add"
                                ) })
                            NavigationRailItem(
                                selected = backStackEntry.value?.destination?.route == "settings",
                                onClick = { navController.navigate("settings") },
                                label= { Text("Settings") },
                                icon = { Icon(
                                    painterResource(id = R.drawable.settingsicon),
                                    contentDescription = "settings"
                                ) })
                        }
                    },
                    content = { innerPadding ->
                        NavHost(navController = navController, startDestination = "expenses") {
                            composable("expenses"){
                                Surface {
                                    var modifier = Modifier
                                        .fillMaxSize()
                                        .padding(innerPadding)
                                }
                                Greeting(name = "Expenses")
                            }
                            composable("reports"){
                                Surface {
                                    var modifier = Modifier
                                        .fillMaxSize()
                                        .padding(innerPadding)
                                }
                                Greeting(name = "Reports")
                            }
                            composable("add"){
                                Surface {
                                    var modifier = Modifier
                                        .fillMaxSize()
                                        .padding(innerPadding)
                                }
                                Greeting(name = "Add")
                            }
                            composable("settings"){
                                Surface {
                                    var modifier = Modifier
                                        .fillMaxSize()
                                        .padding(innerPadding)
                                }
                                Greeting(name = "Settings")
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CashFlowTheme {
        Greeting("Marian")
    }
}