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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.hotelbooking.R
import com.example.hotelbooking.navigation.Route
import com.example.hotelbooking.ui.utility.AppBar
import com.example.hotelbooking.view.auth.components.AuthViewState
import com.example.hotelbooking.view.components.ProfileViewState
import com.example.hotelbooking.viewmodel.AuthViewModel
import com.example.hotelbooking.viewmodel.UsersViewModel


@Composable
internal fun ProfileScreen(
    openEditProfileScreen: () -> Unit,
    openPassWordChangeScreen: () -> Unit,
    logOut: () -> Unit,
    openPropertiesScreen: () -> Unit
) {
    val usersViewModel: UsersViewModel = hiltViewModel()
    val userState by usersViewModel.state.collectAsStateWithLifecycle()

    val authViewModel: AuthViewModel = hiltViewModel()
    val authState by authViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        usersViewModel.getUser()
    }

    ProfileContent(
        userState,
        usersViewModel,
        authState,
        authViewModel,
        openEditProfileScreen,
        openPassWordChangeScreen,
        logOut,
        openPropertiesScreen
    )
}


@Composable
fun ProfileContent(
    userState: ProfileViewState,
    usersViewModel: UsersViewModel = hiltViewModel(),
    authState: AuthViewState,
    authViewModel: AuthViewModel = hiltViewModel(),
    openEditProfileScreen: () -> Unit,
    openPassWordChangeScreen: () -> Unit,
    logOut: () -> Unit,
    openPropertiesScreen: () -> Unit
) {
    Scaffold(
        topBar = {
            AppBar(
                currentScreen = Route.ProfileScreen,
                currentScreenName = stringResource(id = R.string.profile_screen),
                canNavigateBack = false,
                navigateUp = { /*TODO*/ })
        },
    ) { innerpadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding)
                .padding(dimensionResource(id = R.dimen.screenPadding))
                .padding(top = 12.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            userState.user?.let {
                AccountThumbnail(
                    avatar = it.avatar,
                    accountName = it.username,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Divider()
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                AccountSetting(
                    iconSource = R.drawable.baseline_supervisor_account_24,
                    textDescription = "Chỉnh sửa thông tin",
                    onClick = { openEditProfileScreen() }
                )
                AccountSetting(
                    iconSource = R.drawable.baseline_house_24,
                    textDescription = "Sở hữu",
                    onClick = { openPropertiesScreen() }
                )
                AccountSetting(
                    iconSource = R.drawable.baseline_lock_outline_24,
                    textDescription = "Đổi mật khẩu",
                    onClick = { openPassWordChangeScreen() }
                )
                AccountSetting(
                    iconSource = R.drawable.baseline_logout_24,
                    textDescription = "Đăng xuất",
                    onClick = {
                        authViewModel.logout()

                        logOut()
                    }
                )
            }
        }


    }
}

@Composable
fun AccountThumbnail(
    modifier: Modifier = Modifier,
    avatar: String = "https://icons.veryicon.com/png/o/miscellaneous/two-color-icon-library/user-286.png",
    accountName: String = "Tên đăng nhập",
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = avatar,
            contentDescription = accountName,
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
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
fun AccountSetting(
    modifier: Modifier = Modifier,
    @DrawableRes iconSource: Int,
    textDescription: String,
    onClick: () -> (Unit) = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 3.dp),
        verticalAlignment = Alignment.CenterVertically,

        ) {
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