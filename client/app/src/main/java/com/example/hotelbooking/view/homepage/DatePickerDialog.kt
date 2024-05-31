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
    onDateSelected: (LocalDate, DateType) -> Unit,
    minDate: LocalDate?
) {
    val disabledDates = when (selectedDateType){
        DateType.IN -> listOf(LocalDate.now().minusDays(1))
        DateType.OUT -> listOf(LocalDate.now().minusDays(1))
        else -> listOf()
    }
    CalendarDialog(
        state = calendarState,
        config = CalendarConfig(
            monthSelection = true,
            yearSelection = true,
            style = CalendarStyle.MONTH,
            disabledDates = disabledDates,

            ),
        selection = CalendarSelection.Date { selectedDate ->
            val isValidDate = when(selectedDateType) {
                DateType.IN -> selectedDate >= LocalDate.now()
                DateType.OUT -> selectedDate >= LocalDate.now()
                else -> false
            }
            if (isValidDate) {
                if (calendarState.visible) {
                    selectedDateType?.let { dateType ->
                        onDateSelected(selectedDate, dateType)
                    }
                    calendarState.hide()
                }
            }
        }
    )
}

enum class DateType {
    IN, //Ngày nhận
    OUT //Ngày trả
}