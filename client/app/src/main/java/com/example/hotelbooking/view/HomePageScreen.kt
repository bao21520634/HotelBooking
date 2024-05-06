package com.example.hotelbooking.view

import HotelCard
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotelbooking.R
import com.example.hotelbooking.ui.model.Hotel
import com.example.hotelbooking.ui.model.sampleData
import com.example.hotelbooking.ui.utility.ImportantButtonLogin
import com.example.hotelbooking.ui.utility.ImportantButtonMain
import com.example.hotelbooking.ui.utility.InfoTextField

@Composable
fun HomePageScreen(hotelList: List<Hotel>, modifier: Modifier = Modifier){

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp,Alignment.CenterVertically),
    ){
        FilterBlock()
        Text(
            text = "Dành cho bạn",
            style = MaterialTheme.typography.titleLarge,
            color = colorResource(R.color.dark_blue),
            fontWeight = FontWeight.Bold,
        )
        LazyColumn (
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            items(hotelList) {
                HotelCard(hotel = it)
            }
        }

    }
}
@Composable
fun FilterBlock(modifier: Modifier = Modifier){
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = "",
            onValueChange = {},
            modifier = modifier.fillMaxWidth()
        )
        Row (
            modifier = modifier.height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Column (
                modifier = modifier.weight(2/3f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                TextField(value = "", onValueChange = {})
                TextField(value = "", onValueChange = {})
            }
            TextField(
                value = "",
                onValueChange = {},
                modifier = modifier
                    .weight(1 / 3f)
                    .fillMaxHeight()
            )
        }
        ImportantButtonMain(text = "Tìm kiếm", onClick = { /*TODO*/ })
    }
}
@Preview(showBackground = true)
@Composable
fun FilterBlockPreview(){
    FilterBlock()
}
@Preview(showBackground = true)
@Composable
fun HomePageScreenPreview(){
    HomePageScreen(sampleData)
}