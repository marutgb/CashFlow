package com.mgalexandrescu.cashflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Alignment
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mgalexandrescu.cashflow.pages.Add
import com.mgalexandrescu.cashflow.pages.Categories
import com.mgalexandrescu.cashflow.pages.Expenses
import com.mgalexandrescu.cashflow.pages.Settings
import com.mgalexandrescu.cashflow.ui.theme.Background
import com.mgalexandrescu.cashflow.ui.theme.CashFlowTheme
import com.mgalexandrescu.cashflow.ui.theme.Primary
import com.mgalexandrescu.cashflow.ui.theme.TextPrimary

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CashFlowTheme {
                val navController = rememberNavController()
                val backStackEntry = navController.currentBackStackEntryAsState()
                Scaffold(

                    bottomBar = {
                        NavigationBar(
                            containerColor = Background
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                NavigationBarItem(
                                    selected = backStackEntry.value?.destination?.route == "expenses",
                                    onClick = { navController.navigate("expenses") },
                                    icon = {
                                        Icon(
                                            painterResource(id = R.drawable.spendingsicon),
                                            contentDescription = "spendings",
                                            tint = if (backStackEntry.value?.destination?.route == "expenses") Primary else TextPrimary
                                        )
                                    },
                                    label = { Text("Expenses") }
                                )
                                NavigationBarItem(
                                    selected = backStackEntry.value?.destination?.route == "reports",
                                    onClick = { navController.navigate("reports") },
                                    icon = {
                                        Icon(
                                            painterResource(id = R.drawable.reportsicon),
                                            contentDescription = "reports",
                                            tint = if (backStackEntry.value?.destination?.route == "reports") Primary else TextPrimary
                                        )
                                    },
                                    label = { Text("Reports") }
                                )
                                NavigationBarItem(
                                    selected = backStackEntry.value?.destination?.route == "add",
                                    onClick = { navController.navigate("add") },
                                    icon = {
                                        Icon(
                                            painterResource(id = R.drawable.addicon),
                                            contentDescription = "add",
                                            tint = if (backStackEntry.value?.destination?.route == "add") Primary else TextPrimary
                                        )
                                    },
                                    label = { Text("Add") }
                                )
                                NavigationBarItem(
                                    selected = backStackEntry.value?.destination?.route?.startsWith("settings") ?: false,
                                    onClick = { navController.navigate("settings") },
                                    icon = {
                                        Icon(
                                            painterResource(id = R.drawable.settingsicon),
                                            contentDescription = "settings",
                                            tint = if (backStackEntry.value?.destination?.route?.startsWith("settings") ?: false) Primary else TextPrimary
                                        )
                                    },
                                    label = { Text("Settings") }
                                )
                            }
                        }
                    }
,
                    content = { innerPadding ->
                        NavHost(navController = navController, startDestination = "expenses") {
                            composable("expenses"){
                                Surface {
                                    var modifier = Modifier
                                        .fillMaxSize()
                                        .padding(innerPadding)
                                }
                                Expenses(navController, name = "Expenses")
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
                                Add(navController)
                            }
                            composable("settings"){
                                Surface {
                                    var modifier = Modifier
                                        .fillMaxSize()
                                        .padding(innerPadding)
                                }
                                Settings(navController,name = "Settings")
                            }
                            composable("settings/categories"){
                                Surface {
                                    var modifier = Modifier
                                        .fillMaxSize()
                                        .padding(innerPadding)
                                }
                                Categories(navController)
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
