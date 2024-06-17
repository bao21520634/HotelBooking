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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hotelbooking.R
import com.example.hotelbooking.navigation.Route
import com.example.hotelbooking.ui.utility.AppBar
import com.example.hotelbooking.ui.utility.CheckboxWithDescription
import com.example.hotelbooking.ui.utility.EditTextField
import com.example.hotelbooking.viewmodel.HotelsViewModel
import com.example.hotelbooking.viewmodel.UsersViewModel

@Composable
fun PropertiesInformationScreen(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    hotelsViewModel: HotelsViewModel = hiltViewModel(),
    usersViewModel: UsersViewModel = hiltViewModel(),
) {
    val propertiesState by hotelsViewModel.propertiesState.collectAsStateWithLifecycle()

    var name: String by remember { mutableStateOf(propertiesState.name) }
    var location: String by remember { mutableStateOf(propertiesState.address) }
    var description: String by remember { mutableStateOf(propertiesState.description) }


    var facilities = remember {
        mutableStateListOf(
            Facility("Wifi miễn phí"),
            Facility("Điều hòa"),
            Facility("Bể bơi"),
            Facility("Bãi đậu xe miễn phí"),
        )
    }

    Scaffold(
        modifier = Modifier.padding(bottom = 80.dp),
        topBar = {
            AppBar(
                currentScreen = Route.PropertiesInformationScreen,
                currentScreenName = stringResource(id = R.string.propertiesInformation_screen),
                canNavigateBack = false,
                navigateUp = { /*TODO*/ })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(dimensionResource(id = R.dimen.screenPadding)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.columnPadding))
        ) {
            EditTextField(value = name, onValueChange = { name = it }, descriptionText = "Tên")
            EditTextField(
                value = location,
                onValueChange = { location = it },
                descriptionText = "Địa chỉ"
            )
            EditTextField(
                value = description,
                onValueChange = { description = it },
                descriptionText = "Mô tả"
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "Tiện ích",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp
            )
            Column(
            ) {
                FacilitiesList(facilities = facilities)
            }
        }
    }

    hotelsViewModel.updatePropertiesSearchParams(
        name = name,
        address = location,
        description = description,
        facilities = facilities.filter { it.isSelected }.map { it.name },
    )
}

data class Facility(val name: String, var isSelected: Boolean = false)

@Composable
fun FacilitiesList(facilities: MutableList<Facility>) {
    val updateFacilities = rememberUpdatedState(newValue = facilities)

    Column {
        updateFacilities.value.forEachIndexed { index, facility ->
            CheckboxWithDescription(
                checked = facility.isSelected,
                onCheckedChange = {
                    updateFacilities.value[index] = facility.copy(isSelected = it)
                },
                description = facility.name
            )
        }
    }
}