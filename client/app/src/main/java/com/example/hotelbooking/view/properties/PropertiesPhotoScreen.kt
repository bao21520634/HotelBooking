package com.example.hotelbooking.view.properties

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hotelbooking.R
import com.example.hotelbooking.navigation.Route
import com.example.hotelbooking.ui.utility.AppBar
import com.example.hotelbooking.ui.utility.ImageWithDeleteButton

@Composable
fun PropertiesPhotoScreen() {
    Scaffold(
        topBar = {
            AppBar(
                currentScreen = Route.ProfileScreen,
                currentScreenName = "Hình ảnh",
                canNavigateBack = false,
                navigateUp = { /*TODO*/ })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues).padding(8.dp)
        ){
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)

            ) {
                items(imageList){
                    ImageWithDeleteButton(imageSource = it, onDeleteButtonPressed = { /*TODO*/ })
                }
            }
            Spacer(Modifier.height(12.dp))
            OutlinedButton(onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Icon(painter = painterResource(id = R.drawable.baseline_upload_24), contentDescription = null)
                    Text(text = "Thêm ảnh ở đây")
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PropertiesPhotoScreenScreenPreview(){
    PropertiesPhotoScreen()
}
val imageList: List< Int> = listOf(
    R.drawable.hotel_thumbnail,
    R.drawable.koda
)