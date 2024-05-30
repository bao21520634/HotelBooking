package com.example.hotelbooking.ui.utility

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.hotelbooking.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageWithDeleteButton(
    uri : Uri,
    onDeleteButtonPressed: ()-> (Unit), modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = uri
                ),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            )
            Card(
                onClick = onDeleteButtonPressed,
                modifier = Modifier.align(Alignment.TopEnd).wrapContentSize(),
                colors = CardDefaults.cardColors(
                    contentColor = Color.Gray,
                    containerColor = Color.White
                ),
                shape = CircleShape
            ){
                Icon(
                    painter = painterResource(id = R.drawable.baseline_clear_32),
                    contentDescription = null
                )
            }
        }
    }
}