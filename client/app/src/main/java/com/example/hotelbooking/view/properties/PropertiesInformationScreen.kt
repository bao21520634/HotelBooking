package com.example.hotelbooking.view.properties

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hotelbooking.R
import com.example.hotelbooking.navigation.Route
import com.example.hotelbooking.ui.utility.AppBar
import com.example.hotelbooking.ui.utility.CheckboxWithDescription
import com.example.hotelbooking.ui.utility.EditTextField

@Composable
fun PropertiesInformationScreen(){
    var name: String by remember{ mutableStateOf("") }
    var location: String by remember{ mutableStateOf("") }
    var description: String by remember{ mutableStateOf("") }

    var freeWifi: Boolean by remember{ mutableStateOf(false) }
    var airConditioner: Boolean by remember{ mutableStateOf(false) }
    var swimmingPool: Boolean by remember{ mutableStateOf(false) }
    var freeParkingSlot: Boolean by remember{ mutableStateOf(false) }
    Scaffold(
        topBar = {
            AppBar(
                currentScreen = Route.PropertiesInformationScreen,
                currentScreenName = stringResource(id = R.string.propertiesInformation_screen),
                canNavigateBack = false,
                navigateUp = { /*TODO*/ })
        }
    ) {paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(dimensionResource(id = R.dimen.screenPadding)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.columnPadding))
        ) {
            EditTextField(value = name, onValueChange = {name = it}, descriptionText = "Tên")
            EditTextField(value = location, onValueChange = {location = it}, descriptionText = "Địa chỉ")
            EditTextField(value = description, onValueChange = {description = it}, descriptionText = "Mô tả")
            Spacer(Modifier.height(4.dp))
            Text(
                text = "Tiện ích",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Medium
            )
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