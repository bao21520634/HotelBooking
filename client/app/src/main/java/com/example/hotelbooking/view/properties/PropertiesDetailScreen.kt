package com.example.hotelbooking.view.properties

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
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
import com.example.hotelbooking.ui.utility.TextFieldWithIncrement
import com.example.hotelbooking.viewmodel.HotelsViewModel
import com.example.hotelbooking.viewmodel.UsersViewModel

@Composable
fun PropertiesDetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    hotelsViewModel: HotelsViewModel = hiltViewModel(),
    usersViewModel: UsersViewModel = hiltViewModel(),
) {
    val propertiesState by hotelsViewModel.propertiesState.collectAsStateWithLifecycle()

    LaunchedEffect(propertiesState) {
        Log.d("", propertiesState.toString())
    }

    var nofRoom: Int by remember { mutableStateOf(1) }
    var nofAdult: Int by remember { mutableStateOf(1) }
    var nofChildren: Int by remember { mutableStateOf(0) }

    val botRoom: Int = 1;
    val botAdult: Int = 1;
    val botChildren: Int = 0;

    var roomDisabled: Boolean by remember { mutableStateOf(true) }
    var adultDisabled: Boolean by remember { mutableStateOf(true) }
    var childrenDisabled: Boolean by remember { mutableStateOf(true) }


    val bedrooms = remember {
        mutableStateListOf(
            Bedroom("Phòng ngủ (1 giường đơn)"),
            Bedroom("Phòng ngủ (2 giường đơn)"),
            Bedroom("Phòng ngủ (1 giường đôi)")
        )
    }

    val interiors = remember {
        mutableStateListOf(
            Interior("Điều hòa"),
            Interior("Tivi"),
            Interior("Bồn tắm"),
            Interior("Bãi đậu xe miễn phí"),
            Interior("Máy nóng lạnh")
        )
    }

    Scaffold(
        modifier = Modifier.padding(bottom = 80.dp),
        topBar = {
            AppBar(
                currentScreen = Route.PropertiesScreen,
                currentScreenName = stringResource(id = R.string.propertiesDetails_screen),
                canNavigateBack = false,
                navigateUp = { /*TODO*/ })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(dimensionResource(R.dimen.screenPadding))
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.columnPadding))
        ) {
            Column {
                CommonBodyText(text = "Số lượng của loại hình này")
                Spacer(Modifier.height(4.dp))
                TextFieldWithIncrement(
                    value = nofRoom,
                    onIncrementClick = {
                        nofRoom += 1
                        roomDisabled = false
                    },
                    onDecrementClick = {
                        if (nofRoom > botRoom) {
                            nofRoom -= 1;
                            if (nofRoom == botRoom) {
                                roomDisabled = true;
                            }
                        }
                    },
                    isDecDisable = roomDisabled
                )
            }
            Column {
                CommonBodyText(text = "Số người lớn có thể ở")
                Spacer(Modifier.height(4.dp))
                TextFieldWithIncrement(
                    value = nofAdult,
                    onIncrementClick = {
                        nofAdult += 1
                        adultDisabled = false
                    },
                    onDecrementClick = {
                        if (nofAdult > botAdult) {
                            nofAdult -= 1;
                            if (nofAdult == botAdult) {
                                adultDisabled = true;
                            }
                        }
                    },
                    isDecDisable = adultDisabled
                )
            }
            Column {
                CommonBodyText(text = "Số trẻ em có thể ở")
                Spacer(Modifier.height(4.dp))
                TextFieldWithIncrement(
                    value = nofChildren,
                    onIncrementClick = {
                        nofChildren += 1
                        childrenDisabled = false
                    },
                    onDecrementClick = {
                        if (nofChildren > botChildren) {
                            nofChildren -= 1;
                            if (nofChildren == botChildren) {
                                childrenDisabled = true;
                            }
                        }
                    },
                    isDecDisable = childrenDisabled
                )
            }
            Spacer(Modifier.height(4.dp))
            Text(
                text = "Phòng",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                ),
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(4.dp))

            Column {
                BedroomsList(bedrooms = bedrooms)
            }

            Spacer(Modifier.height(4.dp))
            Text(
                text = "Nội thất",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                ),
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(4.dp))

            Column {
                InteriorsList(interiors = interiors)
            }
        }

    }
}

@Composable
fun CommonBodyText(
    text: String,
    color: Color = colorResource(id = R.color.black),
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium,
        modifier = modifier,
        color = color
    )
}

@Composable
fun CommonHeaderText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}

@Composable
fun CommonRowWithTextField(
    modifier: Modifier = Modifier,
    description: String,
    onIncrementClick: () -> (Unit),
    onDecrementClick: () -> (Unit),
    isDisabled: Boolean = false,
    value: Int,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CommonBodyText(text = description, modifier = Modifier.width(160.dp))
        Spacer(Modifier.weight(1f))
        TextFieldWithIncrement(
            value = value,
            onIncrementClick = onIncrementClick,
            onDecrementClick = onDecrementClick,
            isDecDisable = isDisabled
        )
    }
}

data class Interior(val name: String, var isSelected: Boolean = false)
data class Bedroom(val type: String, var quantity: Int = 0, var min: Int = 0)

@Composable
fun InteriorsList(interiors: MutableList<Interior>) {
    val updateInteriors = rememberUpdatedState(newValue = interiors)

    Column {
        updateInteriors.value.forEach { facility ->
            CheckboxWithDescription(
                checked = facility.isSelected,
                onCheckedChange = {
                    updateInteriors.value[updateInteriors.value.indexOf(facility)] =
                        facility.copy(isSelected = it)
                },
                description = facility.name
            )
        }
    }
}

@Composable
fun BedroomsList(bedrooms: MutableList<Bedroom>) {
    val updateBedrooms = rememberUpdatedState(newValue = bedrooms)

    Column {
        updateBedrooms.value.forEach { bedroom ->
            CommonRowWithTextField(
                description = bedroom.type,
                value = bedroom.quantity,
                onIncrementClick = {
                    updateBedrooms.value[updateBedrooms.value.indexOf(bedroom)] =
                        bedroom.copy(quantity = bedroom.quantity + 1)
                },
                onDecrementClick = {
                    if (bedroom.quantity > 0) {
                        updateBedrooms.value[updateBedrooms.value.indexOf(bedroom)] =
                            bedroom.copy(quantity = bedroom.quantity - 1)
                    }
                },
                isDisabled = bedroom.quantity == bedroom.min
            )
        }
    }
}