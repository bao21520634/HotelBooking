package com.example.hotelbooking.view.homepage

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.datedialogssample.DateUtils
import com.example.hotelbooking.R
import com.example.hotelbooking.navigation.Route
import com.example.hotelbooking.ui.utility.AppBar
import com.example.hotelbooking.ui.utility.ImportantButtonMain

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickingScreen() {
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
                showModeToggle = true,
            )
            Spacer(modifier = Modifier.weight(1f))
            ImportantButtonMain(text = "Chọn", onClick = { /*TODO*/ })
        }

    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun DatePickingScreenPreview(){
    DatePickingScreen()
}