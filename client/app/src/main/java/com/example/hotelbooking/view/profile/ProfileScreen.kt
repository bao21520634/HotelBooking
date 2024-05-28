package com.example.hotelbooking.view.profile

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotelbooking.R
import com.example.hotelbooking.navigation.Route
import com.example.hotelbooking.ui.utility.AppBar

@Composable
fun ProfileScreen(openEditProfileScreen:() -> Unit,
                  openPassWordChangeScreen:() ->Unit,
                  logOut:() ->Unit,
                  openPropertiesScreen:() -> Unit){
    Scaffold(
        topBar = {
            AppBar(
                currentScreen = Route.ProfileScreen,
                currentScreenName = stringResource(id = R.string.profile_screen),
                canNavigateBack = false,
                navigateUp = { /*TODO*/ })
        },
    ) {innerpadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding)
                .padding(dimensionResource(id = R.dimen.screenPadding))
                .padding(top = 12.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            AccountThumbnail(
                imageSource = R.drawable.koda,
                modifier = Modifier.fillMaxWidth())
            Divider()
            Column (
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ){
                AccountSetting(
                    iconSource = R.drawable.baseline_supervisor_account_24,
                    textDescription = "Chỉnh sửa thông tin",
                    onClick = {openEditProfileScreen()}
                )
//                AccountSetting(
//                    iconSource = R.drawable.baseline_list_alt_24,
//                    textDescription = "Đã đăng tải",
//                    onClick = {}
//                )
                AccountSetting(
                    iconSource = R.drawable.baseline_house_24,
                    textDescription = "Sở hữu",
                    onClick = {openPropertiesScreen()}
                )
                AccountSetting(
                    iconSource = R.drawable.baseline_lock_outline_24,
                    textDescription = "Đổi mật khẩu",
                    onClick = {openPassWordChangeScreen()}
                )
                AccountSetting(
                    iconSource = R.drawable.baseline_logout_24,
                    textDescription = "Đăng xuất",
                    onClick = {logOut()}
                )
            }
    }


    }
}
@Composable
fun AccountThumbnail(@DrawableRes imageSource: Int,
                     accountName: String = "Tên đăng nhập",
                     modifier: Modifier = Modifier,
    ){
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            painter = painterResource(imageSource),

            // Content Description is not needed here - image is decorative, and setting a null content
            // description allows accessibility services to skip this element during navigation.

            contentDescription = null
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = accountName,
            style = MaterialTheme.typography.titleLarge,
            color = colorResource(R.color.dark_blue),
            fontWeight = FontWeight.Bold
        )
    }
}
@Composable
fun AccountSetting(@DrawableRes iconSource: Int,
                   textDescription: String,
                   onClick: ()->(Unit) = {},
                   modifier: Modifier = Modifier){
    Row(
        modifier = modifier.fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 3.dp),
        verticalAlignment = Alignment.CenterVertically,

    ){
        Image(
            painter = painterResource(id = iconSource),
            contentDescription = null
        )
        Spacer(modifier.width(12.dp))
        Text(
            text = textDescription,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
            )

        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview(){
    ProfileScreen(openEditProfileScreen = {}, openPropertiesScreen =  {}, openPassWordChangeScreen = {}, logOut = {});
}