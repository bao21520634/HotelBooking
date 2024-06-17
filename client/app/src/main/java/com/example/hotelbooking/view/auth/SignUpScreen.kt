package com.example.hotelbooking.view.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.hotelbooking.R
import com.example.hotelbooking.ui.utility.ImportantButtonLogin
import com.example.hotelbooking.ui.utility.InfoTextField
import com.example.hotelbooking.ui.utility.PassWordTextField
import com.example.hotelbooking.view.auth.components.AuthViewState
import com.example.hotelbooking.viewmodel.AuthViewModel


@Composable
internal fun SignUpScreen(
    modifier: Modifier = Modifier,
    openLoginScreen: () -> Unit,
    openHomeScreen: () -> Unit,
) {
    val viewModel: AuthViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    SignUpContent(modifier, state, viewModel, openHomeScreen, openLoginScreen)
}

@Composable
fun SignUpContent(
    modifier: Modifier = Modifier,
    state: AuthViewState,
    viewModel: AuthViewModel = hiltViewModel(),
    openHomeScreen: () -> Unit,
    openLoginScreen: () -> Unit,
) {
    var email: String by remember { mutableStateOf("") };
    var username: String by remember { mutableStateOf("") };
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
            text = "Đăng ký",
            style = MaterialTheme.typography.titleLarge,
            color = colorResource(R.color.dark_blue),
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        InfoTextField(
            value = email,
            onValueChange = { email = it },
            promptText = "Email của bạn",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        );
        InfoTextField(
            value = username,
            onValueChange = { username = it },
            promptText = "Tên của bạn",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        );
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
            promptText = "Xác nhận mật khẩu",
            visible = passwordConfirmVisibility,
            onVisibleClicked = { passwordConfirmVisibility = !passwordConfirmVisibility },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        );

        ImportantButtonLogin(text = "Đăng ký", onAction = {
            viewModel.register(email, username, password, passwordConfirm)
        })

        Row(
            modifier = Modifier.align(Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Bạn đã có tài khoản?",
                fontSize = 14.sp
            )
            Spacer(Modifier.width(4.dp))
            TextButton(
                onClick = {
                    openLoginScreen()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = colorResource(id = R.color.primary)
                )
            ) {
                Text(text = "Đăng nhập")
            }
        }
    }

    LaunchedEffect(state) {
        if (state.message != null) {
            openHomeScreen()
        }
    }
}