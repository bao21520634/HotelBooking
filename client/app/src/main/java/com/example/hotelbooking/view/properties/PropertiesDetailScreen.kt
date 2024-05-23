package com.example.hotelbooking.view.properties

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotelbooking.R
import com.example.hotelbooking.navigation.Route
import com.example.hotelbooking.ui.utility.AppBar
import com.example.hotelbooking.ui.utility.CheckboxWithDescription
import com.example.hotelbooking.ui.utility.TextFieldWithIncrement

@Composable
fun PropertiesDetailScreen(){
    var nofThisType: Int by remember{ mutableStateOf(1) }
    var nofAllowedPeople: Int by remember{ mutableStateOf(1) }

    var nofSingleBedBedRoom: Int by remember{ mutableStateOf(1) }
    var nofDoubleSingleBedBedRoom: Int by remember{ mutableStateOf(1) }
    var nofDoubleBedBedRoom: Int by remember{ mutableStateOf(1) }

    var airConditioner: Boolean by remember{ mutableStateOf(true) }
    var tv: Boolean by remember{ mutableStateOf(true) }
    var showerChamber: Boolean by remember{ mutableStateOf(true)}
    var senShower: Boolean by remember{ mutableStateOf(true)}
    var mayNongLanh: Boolean by remember{ mutableStateOf(true)}

    Scaffold(
        topBar = {
            AppBar(
                currentScreen = Route.PropertiesScreen,
                currentScreenName = stringResource(id = R.string.propertiesDetails_screen),
                canNavigateBack = false,
                navigateUp = { /*TODO*/ })
        }
    ) {paddingValues ->
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
                TextFieldWithIncrement(value = nofThisType, topBoundary = Int.MAX_VALUE, botBoundary = 0, preDefindedWidth = 80.dp)
            }
            Column {
                CommonBodyText(text = "Số người có thể ở")
                Spacer(Modifier.height(4.dp))
                TextFieldWithIncrement(value = nofAllowedPeople, topBoundary = Int.MAX_VALUE, botBoundary = 0, preDefindedWidth = 80.dp)
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
            Column (
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ){
                CommonRowWithTextField(
                    description = "Phòng ngủ\n( 1 giường đơn) ",
                    value = nofSingleBedBedRoom,
                    topBoundary = Int.MAX_VALUE,
                    botBoundary = 0
                )
                CommonRowWithTextField(
                    description = "Phòng ngủ\n( 2 giường đơn) ",
                    value = nofDoubleSingleBedBedRoom,
                    topBoundary = Int.MAX_VALUE,
                    botBoundary = 0
                )
                CommonRowWithTextField(
                    description = "Phòng ngủ\n( 1 giường đôi) ",
                    value = nofDoubleBedBedRoom,
                    topBoundary = Int.MAX_VALUE,
                    botBoundary = 0
                )
                CommonRowWithTextField(
                    description = "Phòng khách",
                    value = nofAllowedPeople,
                    topBoundary = Int.MAX_VALUE,
                    botBoundary = 0
                )
                CommonRowWithTextField(
                    description = "Phòng tắm",
                    value = nofAllowedPeople,
                    topBoundary = Int.MAX_VALUE,
                    botBoundary = 0
                )
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

            Column(){
                CheckboxWithDescription(
                    checked = airConditioner,
                    onCheckedChange = { airConditioner = it },
                    description = "Điều hòa"
                )
                CheckboxWithDescription(
                    checked = tv,
                    onCheckedChange = { tv = it },
                    description = "Tivi"
                )
                CheckboxWithDescription(
                    checked = showerChamber,
                    onCheckedChange = { showerChamber = it },
                    description = "Bồn tắm"
                )
                CheckboxWithDescription(
                    checked = senShower,
                    onCheckedChange = { senShower = it },
                    description = "Bãi đậu xe miễn phí"
                )
                CheckboxWithDescription(
                    checked = mayNongLanh,
                    onCheckedChange = { mayNongLanh = it },
                    description = "Máy nóng lạnh"
                )
            }
        }

    }
}
@Composable
fun CommonBodyText(text: String, modifier: Modifier = Modifier){
    Text(
        text = text,
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium,
        modifier = modifier
    )
}
@Composable
fun CommonHeaderText(text: String, modifier: Modifier = Modifier){
    Text(
        text = text,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}
@Composable
fun CommonRowWithTextField(
    description: String,
    value: Int, topBoundary: Int, botBoundary: Int,
    modifier: Modifier = Modifier){
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        CommonBodyText(text = description)
        Spacer(Modifier.weight(1f))
        TextFieldWithIncrement(value = value, topBoundary = topBoundary, botBoundary = botBoundary)
    }
}
@Preview(showBackground = true)
@Composable
fun PropertiesDetailScreenPreview(){
    PropertiesDetailScreen()
}