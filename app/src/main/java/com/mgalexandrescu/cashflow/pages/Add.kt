package com.mgalexandrescu.cashflow.pages

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mgalexandrescu.cashflow.components.TableRow
import com.mgalexandrescu.cashflow.ui.theme.BackgroundElevated
import com.mgalexandrescu.cashflow.ui.theme.ColumnDivider
import com.mgalexandrescu.cashflow.ui.theme.Primary
import com.mgalexandrescu.cashflow.ui.theme.Shapes
import com.mgalexandrescu.cashflow.ui.theme.TopBarBackground
import com.mgalexandrescu.cashflow.components.UnstyledTextField
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Add(navController: NavController, name: String){
    val recurrences = listOf("None", "Daily", "Weekly", "Monthly", "Yearly")
    var selectedRecurrence by remember { mutableStateOf(recurrences[0]) }

    val categories = listOf("none", "food", "clothing", "transportation", "entertainment", "other")
    var selectedCategory by remember { mutableStateOf(categories[0]) }

    val mContext = LocalContext.current
    val mYear: Int
    val mMonth: Int
    val mDay: Int
    val mCalendar = Calendar.getInstance()
    mYear=mCalendar.get(Calendar.YEAR)
    mMonth=mCalendar.get(Calendar.MONTH)
    mDay=mCalendar.get(Calendar.DAY_OF_MONTH)
    var mDate by remember { mutableStateOf("${mCalendar.get(Calendar.DAY_OF_MONTH)}/${mCalendar.get(Calendar.MONTH)+1}/${mCalendar.get(Calendar.YEAR)}") }
    val mDatePicker = DatePickerDialog(
        mContext, {
            _:DatePicker, selectedYear:Int, selectedMonth:Int, selectedDay:Int -> mCalendar.set(Calendar.YEAR, selectedYear)
            mCalendar.set(Calendar.MONTH, selectedMonth)
            mCalendar.set(Calendar.DAY_OF_MONTH, selectedDay)
        },
        mYear,
        mMonth,
        mDay
    )
    mDatePicker.datePicker.maxDate = mCalendar.timeInMillis

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
                        value = "Amount",
                        onValueChange = {},
                        modifier = Modifier.fillMaxWidth(),
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
                            Text(selectedRecurrence)
                            DropdownMenu(expanded = recurrenceMenuOpened, onDismissRequest = {recurrenceMenuOpened=false}) {
                                for (recurrence in recurrences) {
                                    DropdownMenuItem(text = { Text(recurrence) }, onClick = {
                                        selectedRecurrence = recurrence
                                        recurrenceMenuOpened = false
                                    })
                                }
                            }
                        }
                    }
                    Divider(thickness = 1.dp, color = ColumnDivider)
                    TableRow("Date") {
                        TextButton(onClick = {mDatePicker.show()}){
                            Text(mDate)
                        }
                    }
                    Divider(thickness = 1.dp, color = ColumnDivider)
                    TableRow("Note") { UnstyledTextField(
                        value = "Note",
                        onValueChange = {},
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = TextStyle(
                            textAlign = TextAlign.End
                        )
                    ) }
                    Divider(thickness = 1.dp, color = ColumnDivider)
                    TableRow("Category"){
                        var categoriesMenuOpened by remember { mutableStateOf(false) }
                        TextButton(onClick = {categoriesMenuOpened=true}) {
                            Text(selectedCategory)
                            DropdownMenu(expanded = categoriesMenuOpened, onDismissRequest = {categoriesMenuOpened=false}) {
                                for (category in categories) {
                                    DropdownMenuItem(text = { Row {
                                        Surface(modifier = Modifier.size(16.dp), shape = CircleShape, color = Primary){}
                                        Text(category, modifier = Modifier.padding(start = 8.dp))
                                    } }, onClick = {
                                        selectedCategory = category
                                        categoriesMenuOpened = false
                                    })
                                }
                            }
                        }
                    }
                }
                Button(onClick = {}, modifier = Modifier.padding(16.dp),
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