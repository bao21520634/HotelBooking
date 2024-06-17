package com.example.hotelbooking.view.auth

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.hotelbooking.ui.utility.ActionText
import com.example.hotelbooking.ui.utility.ImportantButtonLogin
import com.example.hotelbooking.ui.utility.InfoTextField
import com.example.hotelbooking.view.auth.components.AuthViewState
import com.example.hotelbooking.viewmodel.AuthViewModel
import com.example.hotelbooking.ui.utility.PassWordTextField

@Composable
internal fun LoginScreen(
    modifier: Modifier = Modifier,
    openSignUpScreen: () -> Unit,
    openHomeScreen: () -> Unit,
    openForgotPasswordScreen: () -> Unit
) {
    val viewModel: AuthViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LoginContent(state = state, viewModel = viewModel, openSignUpScreen = openSignUpScreen, openHomeScreen = openHomeScreen, openForgotPasswordScreen = openForgotPasswordScreen, modifier = modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    state: AuthViewState,
    viewModel: AuthViewModel = hiltViewModel(),
    openSignUpScreen: () -> Unit,
    openHomeScreen: () -> Unit,
    openForgotPasswordScreen: () -> Unit
) {
    var email: String by remember { mutableStateOf("") };
    var password: String by remember { mutableStateOf("") };
    var passWordVisibility: Boolean by remember{ mutableStateOf(false) };

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.screenPadding)),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
    ) {
        Text(
            text = "Đăng nhập",
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
        PassWordTextField(
            value = password,
            onValueChange = { password = it },
            visible = passWordVisibility,
            onVisibleClicked = {passWordVisibility=!passWordVisibility},
            promptText = "Mật khẩu",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        );
        ActionText(
            text = "Quên mật khẩu?",
            action = { openForgotPasswordScreen() },
            modifier = Modifier.align(Alignment.End)
        )
        ImportantButtonLogin(text = "Đăng nhập", onAction = {
            viewModel.login(email, password)
        })

        Row(
            modifier = Modifier.align(Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Bạn chưa có tài khoản?",
                fontSize = 14.sp
            )
            Spacer(Modifier.width(1.dp))
            TextButton(
                onClick = {
                    openSignUpScreen()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = colorResource(id = R.color.primary)
                )
            ) {
                Text(text = "Đăng ký")
            }
        }
    }

    LaunchedEffect(state) {
        if (state.message != null) {
            openHomeScreen()
        }
    }
}