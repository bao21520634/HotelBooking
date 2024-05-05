import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.semantics.SemanticsProperties.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.doanmobileui.ui.utility.ActionText
import com.example.doanmobileui.ui.utility.ImportantButton
import com.example.doanmobileui.ui.utility.InfoTextField

@Composable
fun ForgotPassWordScreen(modifier: Modifier = Modifier){
    var email: String by remember{ mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp,Alignment.CenterVertically),
    ){
        Text(
            text = "Nhập email để khôi phục",
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
        ImportantButton(text = "Gửi", onAction = {})

    }
}
@Preview(showBackground = true)
@Composable
fun ForgotPassWordScreenPreview(){
    ForgotPassWordScreen()
}