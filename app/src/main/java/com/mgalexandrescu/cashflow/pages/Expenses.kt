package com.mgalexandrescu.cashflow.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.mgalexandrescu.cashflow.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Expenses(navController: NavController, name: String) {
    Scaffold (
        topBar={
            MediumTopAppBar(title = { Text("Expenses")}, colors = TopAppBarDefaults.mediumTopAppBarColors(
                titleContentColor = Primary,
                containerColor = TopBarBackground
            ))
        },
        content = {innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)){
                Text(text = "This is the $name page!")
            }
        }
    )
}