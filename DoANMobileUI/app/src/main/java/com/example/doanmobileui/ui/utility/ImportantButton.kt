package com.example.doanmobileui.ui.utility

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ImportantButton(text: String, onAction: ()->(Unit), modifier: Modifier = Modifier){
    OutlinedButton(
        modifier = modifier.fillMaxWidth(),
        onClick = onAction,
        border = BorderStroke(2.dp, color = Color(0xFF0172B2)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = text,
            color = Color(0xFF0172B2)
        )
    }
}