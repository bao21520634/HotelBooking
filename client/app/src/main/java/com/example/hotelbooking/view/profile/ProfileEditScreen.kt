package com.example.hotelbooking.view.profile

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.hotelbooking.R
import com.example.hotelbooking.navigation.Route
import com.example.hotelbooking.ui.theme.PrimaryColor
import com.example.hotelbooking.ui.utility.ActionText
import com.example.hotelbooking.ui.utility.AppBar
import com.example.hotelbooking.ui.utility.EditTextField
import com.example.hotelbooking.view.components.ProfileViewState
import com.example.hotelbooking.viewmodel.UsersViewModel

@Composable
internal fun ProfileEditScreen(usersViewModel: UsersViewModel = hiltViewModel(), navigateUp: () -> Unit) {
    val state by usersViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        usersViewModel.getUser()
    }

    ProfileEditContent(
        state = state,
        navigateUp = navigateUp
    )
}

@Composable
fun ProfileEditContent(
    state: ProfileViewState,
    navigateUp: () -> Unit
) {
    var userName = state.user?.username
    var gender = state.user?.gender
    var about = state.user?.bio
    Scaffold(
        topBar = {
            AppBar(
                currentScreen = Route.MyBookingsScreen,
                currentScreenName = stringResource(id = R.string.profileEdit_screen),
                canNavigateBack = true,
                navigateUp = navigateUp
            )
        },
    ) { innerpadding ->
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

            ) {
                EditTextField(
                    value = userName.toString(),
                    onValueChange = { userName = it },
                    descriptionText = "Tên đăng nhập"
                )
                EditTextField(
                    value = gender.toString(),
                    onValueChange = {},
                    descriptionText = "Giới tính"
                )
                EditTextField(
                    value = if (about != null) about.toString() else "",
                    onValueChange = { about = it },
                    descriptionText = "Giới thiệu"
                )
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedButton(
                    onClick = navigateUp,

                    ) {
                    Text(text = "Hủy")
                }
                Spacer(Modifier.width(16.dp))
                Button(
                    onClick = navigateUp,
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
fun AccountThumbnailEdit(
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
        ActionText(
            text = "Tải ảnh lên",
            action = {}
        )
    }
}
