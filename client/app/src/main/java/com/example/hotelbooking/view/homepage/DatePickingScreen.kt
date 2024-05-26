package com.example.hotelbooking.view.homepage

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.datedialogssample.DateUtils
import com.example.hotelbooking.R
import com.example.hotelbooking.navigation.Route
import com.example.hotelbooking.ui.utility.AppBar
import com.example.hotelbooking.ui.utility.ImportantButtonMain
import androidx.compose.runtime.setValue
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickingScreen(onDateInAction: () -> Unit,
                      onDateOutAction: () -> Unit) {

    var selectedDate by remember {
        mutableStateOf<Long?>(null)
    }
    Scaffold(
        topBar = {
            AppBar(
                currentScreen = Route.HomeDateScreen,
                currentScreenName = stringResource(id = R.string.homepageDate_screen),
                canNavigateBack = true,
                navigateUp = { /*TODO*/ })
        }
    ) {paddingValues ->
        val date = rememberDatePickerState()
        val millisToLocalDate = date.selectedDateMillis?.let {
            DateUtils().convertMillisToLocalDate(it)
        }
        val dateToString = millisToLocalDate?.let {
            DateUtils().dateToString(millisToLocalDate)
        } ?: ""
        Column (
            modifier = Modifier
                .padding(paddingValues)
                .padding(dimensionResource(id = R.dimen.screenPadding))
        ){
            DatePicker(
                dateFormatter = DatePickerFormatter(
                    selectedDateSkeleton = "EE,dd MMM, yyyy",
                ),
                title = {},
                state = date,
                showModeToggle = true
            )
            Spacer(modifier = Modifier.weight(1f))
            ImportantButtonMain(text = "Chọn", onClick = {
                selectedDate = date.selectedDateMillis
                //onDateInAction(DateUtils().dateToString(DateUtils().convertMillisToLocalDate(selectedDate!!)))

            })
        }

    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun DatePickingScreenPreview(){
    DatePickingScreen(onDateInAction = {}, onDateOutAction = {})
}