package com.example.hotelbooking.ui.utility

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.hotelbooking.R
import com.example.hotelbooking.ui.theme.PrimaryColor
import com.example.hotelbooking.view.properties.CommonBodyText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWithIncrement(
    value: Int,
    onIncrementClick: ()->(Unit),
    onDecrementClick: ()->(Unit),
    isDecDisable: Boolean = false,
    preDefindedWidth: Dp = 52.dp, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ){
        Card(
            onClick = onDecrementClick,
            modifier = Modifier.fillMaxHeight(),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                contentColor = PrimaryColor,
                containerColor = Color.Transparent
            ),
            enabled = !isDecDisable,
            border = BorderStroke(2.dp,Color.Gray)
        ){
            Column(
                modifier = Modifier.fillMaxHeight().aspectRatio(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    painterResource(id = R.drawable.baseline_horizontal_rule_24),
                    contentDescription = "add",
                )
            }
        }
        OutlinedCard(
            modifier = Modifier.width(preDefindedWidth).fillMaxHeight(),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                contentColor = Color.Black,
                containerColor = Color.Transparent
            ),
            border = BorderStroke(2.dp,Color.Gray)
        ){
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                CommonBodyText(value.toString())
            }

        }
        Card(
            onClick = onIncrementClick,
            modifier = Modifier.fillMaxHeight(),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                contentColor = PrimaryColor,
                containerColor = Color.Transparent
            ),
            border = BorderStroke(2.dp,Color.Gray)
        ){
            Column(
                modifier = Modifier.fillMaxHeight().aspectRatio(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painterResource(id = R.drawable.baseline_add_24),
                    contentDescription = "sun",
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun TextFieldWithIncrement(){
    //TextFieldWithIncrement(value = 0, topBoundary = Int.MAX_VALUE, botBoundary = 0)
}