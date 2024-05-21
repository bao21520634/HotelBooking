package com.example.hotelbooking.view.profile

import androidx.annotation.DrawableRes
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
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotelbooking.BottomNavigationBar
import com.example.hotelbooking.R
import com.example.hotelbooking.navigation.Route
import com.example.hotelbooking.ui.utility.AppBar

@Composable
fun ProfileScreen(){
    Scaffold(
        topBar = {
            AppBar(
                currentScreen = Route.FavoriteScreen,
                currentScreenName = "Thông tin cá nhân",
                canNavigateBack = false,
                navigateUp = { /*TODO*/ })
        },
    ) {innerpadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding)
        ) {
            AccountThumbnail(modifier = Modifier.fillMaxWidth())
            Divider()
            Column (
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ){
                AccountSetting(iconSource = R.drawable.baseline_star_24,
                    textDescription = "Chỉnh sửa thông tin")
                AccountSetting(iconSource = R.drawable.baseline_star_24,
                    textDescription = "Đã đăng tải")
                AccountSetting(iconSource = R.drawable.baseline_star_24,
                    textDescription = "Đổi mật khẩu")
                AccountSetting(iconSource = R.drawable.baseline_star_24,
                    textDescription = "Đăng xuất")
                AccountSetting(iconSource = R.drawable.baseline_star_24,
                    textDescription = "Sở hữu")
            }
    }


    }
}
@Composable
fun AccountThumbnail(modifier: Modifier = Modifier){
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            painter = painterResource(R.drawable.koda),

            // Content Description is not needed here - image is decorative, and setting a null content
            // description allows accessibility services to skip this element during navigation.

            contentDescription = null
        )
        Text(
            text = "Tên đăng nhập",
            style = MaterialTheme.typography.titleLarge,
            color = colorResource(R.color.dark_blue),
            fontWeight = FontWeight.Bold
        )
    }
}
@Composable
fun AccountSetting(@DrawableRes iconSource: Int,
                   textDescription: String,
                   action: ()->(Unit) = {},
                   modifier: Modifier = Modifier){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,

    ){
        Image(
            painter = painterResource(id = iconSource),
            contentDescription = null
        )
        Spacer(modifier.width(4.dp))
        Text(
            text = textDescription,
            fontSize = 18.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview(){
    ProfileScreen();
}