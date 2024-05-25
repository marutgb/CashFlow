package com.mgalexandrescu.cashflow.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mgalexandrescu.cashflow.components.TableRow
import com.mgalexandrescu.cashflow.ui.theme.BackgroundElevated
import com.mgalexandrescu.cashflow.ui.theme.ColumnDivider
import com.mgalexandrescu.cashflow.ui.theme.Divider
import com.mgalexandrescu.cashflow.ui.theme.Primary
import com.mgalexandrescu.cashflow.ui.theme.Shapes
import com.mgalexandrescu.cashflow.ui.theme.TopBarBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Settings(navController: NavController, name: String) {
    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = { Text("Settings") }, colors = TopAppBarDefaults.mediumTopAppBarColors(
                    titleContentColor = Primary,
                    containerColor = TopBarBackground
                )
            )
        },
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .clip(Shapes.large)
                        .background(BackgroundElevated)
                ) {
                    TableRow("Categories", hasArrow = true, modifier = Modifier.clickable {
                            navController.navigate("settings/categories") }
                    )
                    Divider(thickness = 1.dp, color = ColumnDivider)
                    TableRow("Erase all data", isDestructive = true)
                }

            }
        }
    )
}