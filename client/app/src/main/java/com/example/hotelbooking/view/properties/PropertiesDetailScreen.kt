package com.example.hotelbooking.view.properties

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
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
                currentScreenName = "Thông tin",
                canNavigateBack = false,
                navigateUp = { /*TODO*/ })
        }
    ) {paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            Column {
                CommonText(text = "Số lượng của loại hình này")
                TextFieldWithIncrement(value = nofThisType, topBoundary = Int.MAX_VALUE, botBoundary = 0)
            }
            Column {
                CommonText(text = "Số người có thể ở")
                TextFieldWithIncrement(value = nofAllowedPeople, topBoundary = Int.MAX_VALUE, botBoundary = 0)
            }
            Row{
                CommonText(text = "Phòng ngủ( 1 giường đơn)")
                Spacer(Modifier.weight(1f))
                TextFieldWithIncrement(value = nofSingleBedBedRoom, topBoundary = Int.MAX_VALUE, botBoundary = 0)
            }
            Row{
                CommonText(text = "Phòng ngủ( 2 giường đơn)")
                Spacer(Modifier.weight(1f))
                TextFieldWithIncrement(value = nofDoubleSingleBedBedRoom, topBoundary = Int.MAX_VALUE, botBoundary = 0)
            }
            Row{
                CommonText(text = "Phòng ngủ( 1 giường đôi)")
                Spacer(Modifier.weight(1f))
                TextFieldWithIncrement(value = nofDoubleBedBedRoom, topBoundary = Int.MAX_VALUE, botBoundary = 0)
            }
            Row{
                CommonText(text = "Phòng khách")
                Spacer(Modifier.weight(1f))
                TextFieldWithIncrement(value = nofAllowedPeople, topBoundary = Int.MAX_VALUE, botBoundary = 0)
            }
            Row{
                CommonText(text = "Nội thất")
                Spacer(Modifier.weight(1f))
                TextFieldWithIncrement(value = nofAllowedPeople, topBoundary = Int.MAX_VALUE, botBoundary = 0)
            }
            CommonText(text = "Nội thất")
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
fun CommonText(text: String, modifier: Modifier = Modifier){
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.Bold
    )
}
@Preview(showBackground = true)
@Composable
fun PropertiesDetailScreenPreview(){
    PropertiesDetailScreen();
}