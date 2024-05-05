package com.example.doanmobileui.ui.utility

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.doanmobileui.R

@Composable
fun InfoTextField(value: String, onValueChange: (String) -> (Unit),
                  promptText: String,
                  modifier: Modifier = Modifier)
{
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        value = value,
        singleLine = true,
        onValueChange = onValueChange,
        label = {Text(promptText)}
    )
}