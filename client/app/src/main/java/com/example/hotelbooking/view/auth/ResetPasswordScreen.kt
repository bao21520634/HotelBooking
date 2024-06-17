package com.example.hotelbooking.view.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotelbooking.R
import com.example.hotelbooking.ui.utility.ImportantButtonLogin
import com.example.hotelbooking.ui.utility.PassWordTextField

@Composable
fun ResetPasswordScreen(
    modifier: Modifier = Modifier,
    openLoginScreen: () -> Unit
) {
    var email: String by remember { mutableStateOf("") };
    var password: String by remember { mutableStateOf("") };
    var passwordConfirm: String by remember { mutableStateOf("") };

    var passWordVisibility: Boolean by remember { mutableStateOf(false) };
    var passwordConfirmVisibility: Boolean by remember { mutableStateOf(false) };

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.screenPadding)),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
    ) {
        Text(
            text = "Đặt lại mật khẩu",
            style = MaterialTheme.typography.titleLarge,
            color = colorResource(R.color.dark_blue),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        PassWordTextField(
            value = password,
            onValueChange = { password = it },
            visible = passWordVisibility,
            onVisibleClicked = { passWordVisibility = !passWordVisibility },
            promptText = "Mật khẩu",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        );
        PassWordTextField(
            value = passwordConfirm,
            onValueChange = { passwordConfirm = it },
            visible = passwordConfirmVisibility,
            onVisibleClicked = { passwordConfirmVisibility = !passwordConfirmVisibility },
            promptText = "Xác nhận mật khẩu",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        );
        ImportantButtonLogin(text = "Xác nhận", onAction = { openLoginScreen() })
    }
}