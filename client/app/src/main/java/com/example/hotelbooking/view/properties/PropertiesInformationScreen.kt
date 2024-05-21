package com.example.hotelbooking.view.properties

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.hotelbooking.navigation.Route
import com.example.hotelbooking.ui.utility.AppBar
import com.example.hotelbooking.ui.utility.CheckboxWithDescription
import com.example.hotelbooking.ui.utility.EditTextField

@Composable
fun PropertiesInformationScreen(){
    var name: String by remember{ mutableStateOf("") }
    var location: String by remember{ mutableStateOf("") }
    var description: String by remember{ mutableStateOf("") }

    var freeWifi: Boolean by remember{ mutableStateOf(true) }
    var airConditioner: Boolean by remember{ mutableStateOf(true) }
    var swimmingPool: Boolean by remember{ mutableStateOf(true) }
    var freeParkingSlot: Boolean by remember{ mutableStateOf(true) }
    Scaffold(
        topBar = {
            AppBar(
                currentScreen = Route.PropertiesScreen,
                currentScreenName = "Thông tin",
                canNavigateBack = false,
                navigateUp = { /*TODO*/ })
        }
    ) {paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            EditTextField(value = name, onValueChange = {name = it}, descriptionText = "Tên")
            EditTextField(value = location, onValueChange = {location = it}, descriptionText = "Vị trí")
            EditTextField(value = description, onValueChange = {description = it}, descriptionText = "Mô tả")
            Text(text = "Tiện ích")
            Column(

            ){
                CheckboxWithDescription(
                    checked = freeWifi,
                    onCheckedChange = { freeWifi = it },
                    description = "Wifi miễn phí"
                )
                CheckboxWithDescription(
                    checked = airConditioner,
                    onCheckedChange = { airConditioner = it },
                    description = "Điều hòa"
                )
                CheckboxWithDescription(
                    checked = swimmingPool,
                    onCheckedChange = { swimmingPool = it },
                    description = "Bể bơi"
                )
                CheckboxWithDescription(
                    checked = freeParkingSlot,
                    onCheckedChange = { freeParkingSlot = it },
                    description = "Bãi đậu xe miễn phí"
                )
            }
        }

    }
}
@Preview(showBackground = true)
@Composable
fun PropertiesInformationScreenPreview(){
    PropertiesInformationScreen();
}