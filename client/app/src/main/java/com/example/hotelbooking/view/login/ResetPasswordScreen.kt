package com.example.hotelbooking.view.login

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
fun ResetPasswordScreen(modifier: Modifier = Modifier,
                        openLoginScreen:() ->Unit){
    var password: String by remember{ mutableStateOf("") };
    var password_Confirm: String by remember{ mutableStateOf("") };

    var passWordVisibility: Boolean by remember{ mutableStateOf(false) };
    var password_ConfirmVisibility: Boolean by remember{ mutableStateOf(false) };
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.screenPadding)),
        verticalArrangement = Arrangement.spacedBy(16.dp,Alignment.CenterVertically),
    ){
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
            onValueChange = {password = it},
            visible = passWordVisibility,
            onVisibleClicked = {passWordVisibility=!passWordVisibility},
            promptText = "Mật khẩu",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        );
        PassWordTextField(
            value = password_Confirm,
            onValueChange = {password_Confirm = it},
            visible = password_ConfirmVisibility,
            onVisibleClicked = {password_ConfirmVisibility=!password_ConfirmVisibility},
            promptText = " Xác nhận mật khẩu",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        );
        ImportantButtonLogin(text = "Xác nhận", onAction = {openLoginScreen()})

//        Row(
//            modifier = Modifier.align(Alignment.Start),
//            verticalAlignment = Alignment.CenterVertically
//        ){
//            Text(
//                text= "Bạn đã có tài khoản?",
//                fontSize = 14.sp
//            )
//            Spacer(Modifier.width(4.dp))
//            TextButton(onClick = {
//                openLoginScreen()
//            },
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color.Transparent,
//                    contentColor = colorResource(id = R.color.primary)
//                )
//            ) {
//                Text(text = "Đăng nhập")
//            }
//        }
    }
}
@Preview(showBackground = true)
@Composable
fun ResetPasswordScreenPreview(){
    ResetPasswordScreen(openLoginScreen = {})
}