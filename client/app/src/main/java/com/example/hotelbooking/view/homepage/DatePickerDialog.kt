package com.example.hotelbooking.view.homepage

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import java.time.LocalDate
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialog(
    calendarState: com.maxkeppeker.sheets.core.models.base.SheetState,
    selectedDateType: DateType?,
    onDateSelected: (LocalDate, DateType) -> Unit
) {
    CalendarDialog(
        state = calendarState,
        config = CalendarConfig(
            monthSelection = true,
            yearSelection = true,
            style = CalendarStyle.MONTH,
            disabledDates = listOf(LocalDate.now().plusDays(7))
        ),
        selection = CalendarSelection.Date { selectedDate ->
            if (calendarState.visible) {
                selectedDateType?.let { dateType ->
                    onDateSelected(selectedDate, dateType)
                }
                calendarState.hide()
            }
        }
    )
}

enum class DateType {
    IN, //Ngày nhận
    OUT //Ngày trả
}