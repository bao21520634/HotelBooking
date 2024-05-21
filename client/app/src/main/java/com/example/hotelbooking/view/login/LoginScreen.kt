package com.example.hotelbooking.view.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hotelbooking.BottomBarScreen
import com.example.hotelbooking.R
import com.example.hotelbooking.navigation.Route
import com.example.hotelbooking.ui.utility.ActionText
import com.example.hotelbooking.ui.utility.ImportantButtonLogin
import com.example.hotelbooking.ui.utility.InfoTextField

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    openSignUpScreen:() -> Unit,
    openHomeScreen:() ->Unit,
    openForgotPasswordScreen:() -> Unit
){
    var username: String by remember{ mutableStateOf("") };
    var password: String by remember{ mutableStateOf("") };
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
    ){
        Text(
            text = "Đăng nhập",
            style = MaterialTheme.typography.titleLarge,
            color = colorResource(R.color.dark_blue),
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        InfoTextField(
            value = username,
            onValueChange = {username = it},
            promptText = "Tên đăng nhập",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        );
        InfoTextField(
            value = password,
            onValueChange = {password = it},
            promptText = "Mật khẩu",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        );
        ActionText(
            text = "Quên mật khẩu?",
            action = {openForgotPasswordScreen()},
            modifier = Modifier.align(Alignment.End)
        )
        ImportantButtonLogin(text = "Đăng nhập", onAction = {
            openHomeScreen()
        })

        Row(
            modifier = Modifier.align(Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text= "Bạn chưa có tài khoản?",
                fontSize = 14.sp
            )
            Spacer(Modifier.width(1.dp))
            TextButton(onClick = {
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
}
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview(){
    LoginScreen(openHomeScreen = {}, openSignUpScreen = {}, openForgotPasswordScreen = {})
}