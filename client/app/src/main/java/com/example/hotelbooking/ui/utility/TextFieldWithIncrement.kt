package com.example.hotelbooking.ui.utility

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.hotelbooking.R

@Composable
fun TextFieldWithIncrement(value: Int, topBoundary: Int, botBoundary: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
    ){
        Button(onClick = { if(value<topBoundary){value.inc() }}) {
            Icon(
                Icons.Rounded.Add,
                contentDescription = "add"
            )
        }
        Text(text = value.toString(), modifier = Modifier.weight(1f))
        Button(onClick = { if(value>botBoundary){value.dec() } }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_minimize_24),
                contentDescription = "add"
            )
        }
    }
}