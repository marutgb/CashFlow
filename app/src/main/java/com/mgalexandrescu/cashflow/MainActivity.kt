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
import com.mgalexandrescu.cashflow.ui.theme.CashFlowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CashFlowTheme {

                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            NavigationRailItem(
                                selected = false,
                                onClick = { /*TODO*/ },
                                label= { Text("Expenses") },
                                icon = { Icon(
                                    painterResource(id = R.drawable.spendingsicon),
                                    contentDescription = "spendings"
                                ) })
                            NavigationRailItem(
                                selected = false,
                                onClick = { /*TODO*/ },
                                label= { Text("Reports") },
                                icon = { Icon(
                                    painterResource(id = R.drawable.reportsicon),
                                    contentDescription = "reports"
                                ) })
                            NavigationRailItem(
                                selected = false,
                                onClick = { /*TODO*/ },
                                label= { Text("Add") },
                                icon = { Icon(
                                    painterResource(id = R.drawable.addicon),
                                    contentDescription = "add"
                                ) })
                            NavigationRailItem(
                                selected = false,
                                onClick = { /*TODO*/ },
                                label= { Text("Settings") },
                                icon = { Icon(
                                    painterResource(id = R.drawable.settingsicon),
                                    contentDescription = "settings"
                                ) })
                        }
                    },
                    content = { it ->
                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(it),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            Greeting(
                                name = "Marutzu"
                            )
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