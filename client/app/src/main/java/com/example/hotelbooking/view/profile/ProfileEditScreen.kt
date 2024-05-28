package com.example.hotelbooking.view.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hotelbooking.R
import com.example.hotelbooking.navigation.Route
import com.example.hotelbooking.ui.model.Gender
import com.example.hotelbooking.ui.theme.PrimaryColor
import com.example.hotelbooking.ui.utility.ActionText
import com.example.hotelbooking.ui.utility.AppBar
import com.example.hotelbooking.ui.utility.EditTextField

@Composable
fun ProfileEditScreen(){
    var userName: String by remember{ mutableStateOf("") }
    var gender: Gender by remember{ mutableStateOf(Gender.MALE) }
    var about: String by remember{ mutableStateOf("") }

    Scaffold(
        topBar = {
            AppBar(
                currentScreen = Route.MyBookingsScreen,
                currentScreenName = stringResource(id = R.string.profileEdit_screen),
                canNavigateBack = true,
                navigateUp = {  })
        },
    ) {innerpadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding)
                .padding(dimensionResource(id = R.dimen.screenPadding)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.columnPadding))

        ) {
            AccountThumbnailEdit(modifier = Modifier.fillMaxWidth())
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)

            ){
                EditTextField(value = userName,
                    onValueChange = {userName = it},
                    descriptionText = "Tên đăng nhập")
                EditTextField(value = gender.toString(),
                    onValueChange = {},
                    descriptionText = "Giới tính")
                EditTextField(value = about,
                    onValueChange = {about = it},
                    descriptionText = "Giới thiệu")
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ){
                OutlinedButton(
                    onClick = { /*TODO*/ },

                    ) {
                    Text(text = "Hủy")
                }
                Spacer(Modifier.width(16.dp))
                Button(onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = PrimaryColor
                    )
                ) {
                    Text(text = "Lưu")
                }
            }

        }
    }

}
@Composable
fun AccountThumbnailEdit(modifier: Modifier = Modifier){
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            painter = painterResource(R.drawable.koda),

            // Content Description is not needed here - image is decorative, and setting a null content
            // description allows accessibility services to skip this element during navigation.

            contentDescription = null
        )
        ActionText(
            text = "Tải ảnh lên",
            action = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileEditScreenPreview(){
    ProfileEditScreen();
}