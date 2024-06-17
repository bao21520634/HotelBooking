package com.example.hotelbooking.ui.utility

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.hotelbooking.ui.theme.PrimaryColor
import com.example.hotelbooking.view.properties.CommonBodyText

@Composable
fun CheckboxWithDescription(checked: Boolean, onCheckedChange: (Boolean)->(Unit), description: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = PrimaryColor,
                checkmarkColor = Color.White
            )
        )
        CommonBodyText(text = description)
        /*
        Text(
            if (checked) "Checkbox is checked" else "Checkbox is unchecked"
        )
         */
    }

}