package com.example.hotelbooking.ui.utility

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hotelbooking.view.properties.CommonBodyText

@Composable
fun EditTextField(value: String, onValueChange: (String) -> (Unit),
                  descriptionText: String
                  ,modifier: Modifier = Modifier){
    Column(
        modifier = modifier.fillMaxWidth()
    ){
        CommonBodyText(text = descriptionText)
        Spacer(modifier.width(4.dp))
        InfoTextField(value = value, onValueChange = onValueChange, promptText = "", modifier = Modifier.fillMaxWidth())
    }
}