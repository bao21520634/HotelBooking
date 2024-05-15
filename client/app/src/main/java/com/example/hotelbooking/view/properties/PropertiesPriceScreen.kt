package com.example.hotelbooking.view.properties

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotelbooking.R
import com.example.hotelbooking.navigation.Route
import com.example.hotelbooking.ui.utility.AppBar

@Composable
fun PropertiesPriceScreen() {
    var giaNgayThuong: Long by remember { mutableStateOf(1000000) }
    var giaCuoiTuan: Long by remember { mutableStateOf(1200000) }
    Scaffold(
        topBar = {
            AppBar(
                currentScreen = Route.PropertiesPriceScreen,
                currentScreenName = "Giá cả",
                canNavigateBack = false,
                navigateUp = { /*TODO*/ })
        }
    ) {paddingValues ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(dimensionResource(R.dimen.screenPadding))
        ){
            Column {
                CommonHeaderText(text = "Giá/đêm( ngày thường)")
                PriceRow(price = giaNgayThuong,
                    onPriceChange = {giaNgayThuong = it.toLong()},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
            Column {
                CommonHeaderText(text = "Giá/đêm( cuối tuần)")
                PriceRow(price = giaCuoiTuan,
                    onPriceChange = {giaCuoiTuan = it.toLong()},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }
    }

}
@Composable
fun PriceRow(price: Long, onPriceChange: (String) -> (Unit),modifier: Modifier = Modifier){
    Row(
        modifier = modifier.height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedCard(
            modifier = Modifier
                .weight(1 / 4f),
            border = BorderStroke(2.dp, Color.Gray),
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.roundedCornerPadding))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                CommonBodyText(
                    text = "VNĐ",
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
        Spacer(Modifier.width(8.dp))
        OutlinedCard(
            modifier = Modifier
                .weight(1f),
            border = BorderStroke(2.dp, Color.Gray),
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.roundedCornerPadding))
        ) {
            BasicTextField(
                value = price.toString(),
                onValueChange = onPriceChange,
                modifier = Modifier.padding(8.dp).fillMaxWidth(),
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                ),
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PropertiesPriceScreenPreview(){
    PropertiesPriceScreen()
}