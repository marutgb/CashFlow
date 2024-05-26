package com.mgalexandrescu.cashflow.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.marosseleng.compose.material3.datetimepickers.date.ui.dialog.DatePickerDialog
import com.mgalexandrescu.cashflow.components.TableRow
import com.mgalexandrescu.cashflow.ui.theme.BackgroundElevated
import com.mgalexandrescu.cashflow.ui.theme.ColumnDivider
import com.mgalexandrescu.cashflow.ui.theme.Primary
import com.mgalexandrescu.cashflow.ui.theme.Shapes
import com.mgalexandrescu.cashflow.ui.theme.TopBarBackground
import com.mgalexandrescu.cashflow.components.UnstyledTextField
import com.mgalexandrescu.cashflow.models.Recurrence
import com.mgalexandrescu.cashflow.viewmodels.vm
import java.time.LocalDate
import com.mgalexandrescu.cashflow.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun Add(navController: NavController, vm: vm = viewModel()){
    val state by vm.uiState.collectAsState()
    val recurrences = listOf(Recurrence.Daily, Recurrence.Weekly, Recurrence.Monthly, Recurrence.Yearly)
    val categories = listOf("none", "food", "clothing", "transportation", "entertainment", "other")


    Scaffold (
        topBar={
            MediumTopAppBar(title = { Text("Add Expense") }, colors = TopAppBarDefaults.mediumTopAppBarColors(
                titleContentColor = Primary,
                containerColor = TopBarBackground
            ))
        },
        content = {innerPadding ->
            Column(modifier = Modifier.padding(innerPadding), horizontalAlignment = Alignment.CenterHorizontally) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .clip(Shapes.large)
                        .background(BackgroundElevated)
                ) {
                    TableRow("Amount") { UnstyledTextField(
                        value = "${state.amount}",
                        onValueChange = vm::setAmount,
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("0") },
                        arrangement = Arrangement.End,
                        maxLines = 1,
                        textStyle = TextStyle(
                            textAlign = TextAlign.End
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        )
                    ) }
                    Divider(thickness = 1.dp, color = ColumnDivider)
                    TableRow("Recurrence"){
                        var recurrenceMenuOpened by remember { mutableStateOf(false) }
                        TextButton(onClick = {recurrenceMenuOpened=true}) {
                            Text(state.recurrence.name)
                            DropdownMenu(expanded = recurrenceMenuOpened, onDismissRequest = {recurrenceMenuOpened=false}) {
                                for (recurrence in recurrences) {
                                    DropdownMenuItem(text = { Text(recurrence.name) }, onClick = {
                                        vm.setRecurrence(recurrence)
                                        recurrenceMenuOpened = false
                                    })
                                }
                            }
                        }
                    }
                    Divider(thickness = 1.dp, color = ColumnDivider)
                    var datePickerShowing by remember { mutableStateOf(false) }
                    TableRow("Date") {
                        TextButton(onClick = { datePickerShowing = true }) {
                            Text(state.date.toString())
                        }
                        if (datePickerShowing) {
                            DatePickerDialog(
                                onDismissRequest = { datePickerShowing = false },
                                onDateChange = {it ->
                                               vm.setDate(it)
                                    datePickerShowing = false
                                },
                                initialDate = state.date,
                                title = {Text("Select expense date", style = Typography.titleLarge)},
                            )
                        }
                    }
                    Divider(thickness = 1.dp, color = ColumnDivider)
                    TableRow("Note") { UnstyledTextField(
                        value = state.note ?: "",
                        placeholder = { Text("Optional") },
                        arrangement = Arrangement.End,
                        onValueChange = vm::setNote,
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = TextStyle(
                            textAlign = TextAlign.Right
                        )
                    ) }
                    Divider(thickness = 1.dp, color = ColumnDivider)
                    TableRow("Category"){
                        var categoriesMenuOpened by remember { mutableStateOf(false) }
                        TextButton(onClick = {categoriesMenuOpened=true}) {
                            Text(state.category ?: "Select a category")
                            DropdownMenu(expanded = categoriesMenuOpened, onDismissRequest = {categoriesMenuOpened=false}) {
                                for (category in categories) {
                                    DropdownMenuItem(text = { Row {
                                        Surface(modifier = Modifier.size(16.dp), shape = CircleShape, color = Primary){}
                                        Text(category, modifier = Modifier.padding(start = 8.dp))
                                    } }, onClick = {
                                        vm.setCategory(category)
                                        categoriesMenuOpened = false
                                    })
                                }
                            }
                        }
                    }
                }
                Button(onClick = {vm::submitExpense}, modifier = Modifier.padding(16.dp),
                    colors = buttonColors(
                        containerColor = Primary
                    ), shape = Shapes.large
                    ){
                    Text("Submit expense")
                }
            }
        }
    )
}