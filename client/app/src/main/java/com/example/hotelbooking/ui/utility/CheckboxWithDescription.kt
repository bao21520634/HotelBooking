package com.example.hotelbooking.ui.utility

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hotelbooking.view.properties.CommonBodyText

@Composable
fun CheckboxWithDescription(checked: Boolean, onCheckedChange: (Boolean)->(Unit), description: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        Spacer(modifier.width(8.dp))
        CommonBodyText(text = description)
        /*
        Text(
            if (checked) "Checkbox is checked" else "Checkbox is unchecked"
        )
         */
    }

}