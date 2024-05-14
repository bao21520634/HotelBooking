package com.example.hotelbooking.view.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotelbooking.R
import com.example.hotelbooking.ui.utility.ActionText
import com.example.hotelbooking.ui.utility.ImportantButtonLogin
import com.example.hotelbooking.ui.utility.InfoTextField

@Composable
fun SignUpScreen(modifier: Modifier = Modifier){
    var email: String by remember{ mutableStateOf("") };
    var username: String by remember{ mutableStateOf("") };
    var password: String by remember{ mutableStateOf("") };
    var password_Confirm: String by remember{ mutableStateOf("") };

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp,Alignment.CenterVertically),
    ){
        Text(
            text = "Đăng ký",
            style = MaterialTheme.typography.titleLarge,
            color = colorResource(R.color.dark_blue),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        InfoTextField(
            value = email,
            onValueChange = {email = it},
            promptText = "Email của bạn",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        );
        InfoTextField(
            value = username,
            onValueChange = {username = it},
            promptText = "Tên đăng nhập",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        );
        InfoTextField(value = password,
            onValueChange = {password = it},
            promptText = "Mật khẩu",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        );
        InfoTextField(value = password,
            onValueChange = {password_Confirm = it},
            promptText = "Xác nhận mật khẩu",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        );

        ImportantButtonLogin(text = "Đăng nhập", onAction = {})

        Row(
            modifier = Modifier.align(Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text= "Bạn đã có tài khoản?"
            )
            Spacer(Modifier.width(4.dp))
            ActionText(text = "Đăng nhập", action = { /*TODO*/ })
        }
    }
}
@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview(){
    SignUpScreen()
}