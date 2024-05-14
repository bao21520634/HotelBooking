package com.example.hotelbooking.ui.utility

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.hotelbooking.R

@Composable
fun ImageWithDeleteButton(@DrawableRes imageSource: Int, onDeleteButtonPressed: ()-> (Unit), modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = imageSource),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(160.dp).fillMaxWidth()
            )
            FilledIconButton(
                onClick = onDeleteButtonPressed,
                modifier = Modifier.align(Alignment.TopEnd),

                ) {
                Icon(
                    Icons.Default.Clear,
                    contentDescription = null
                )
            }
        }
    }
}