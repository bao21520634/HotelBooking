package com.example.hotelbooking.ui.utility

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotelbooking.R
import com.example.hotelbooking.ui.theme.PrimaryColor

@Composable
fun PassWordTextField(value: String, onValueChange: (String) -> (Unit),
                      visible: Boolean,
                      onVisibleClicked: ()->(Unit),
                      promptText: String,
                      modifier: Modifier = Modifier
)
{
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(20.dp),
        value = value,
        singleLine = true,
        onValueChange = onValueChange,
        label = { Text(promptText, fontSize = 14.sp) },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            cursorColor = PrimaryColor,
            focusedIndicatorColor = PrimaryColor,
            focusedLabelColor = PrimaryColor
        ),
        textStyle = TextStyle(
            textAlign = TextAlign.Left,
            fontSize = 14.sp
        ),
        visualTransformation  = if (visible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val description = if (visible) "Hide password" else "Show password"
            IconButton(onClick = onVisibleClicked){
                if (visible)
                {
                    Icon(painter = painterResource(id = R.drawable.baseline_visibility_24), contentDescription = null)
                }
                else{
                    Icon(painter = painterResource(id = R.drawable.baseline_visibility_off_24), contentDescription = null)
                }
            }
        }
    )
}