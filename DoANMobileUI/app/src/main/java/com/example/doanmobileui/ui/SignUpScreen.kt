import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.semantics.SemanticsProperties.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen
import com.example.doanmobileui.ui.utility.ActionText
import com.example.doanmobileui.ui.utility.ImportantButton
import com.example.doanmobileui.ui.utility.InfoTextField
import com.example.doanmobileui.ui.theme.bluePrimary

@Composable
fun SignUpScreen(modifier: Modifier = Modifier, openLoginScreen: () -> Unit){
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
            color = Color.Black,
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
        ImportantButton(text = "Đăng ký", onAction = {})

        Row(
            modifier = Modifier.align(Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text= "Bạn đã có tài khoản?"
            )
            Spacer(Modifier.width(4.dp))
            TextButton(onClick = { openLoginScreen() }
            ) {
                Text(text = "Đăng nhập", color = bluePrimary)
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview(){
    SignUpScreen(openLoginScreen = { } )
}