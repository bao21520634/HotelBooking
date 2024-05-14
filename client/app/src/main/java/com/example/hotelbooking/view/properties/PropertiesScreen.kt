package com.example.hotelbooking.view.properties

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hotelbooking.R
import com.example.hotelbooking.ui.utility.AppBar
import com.example.hotelbooking.navigation.Route
import com.example.hotelbooking.ui.model.Property
import com.example.hotelbooking.ui.model.properties


@Composable
fun PropertiesScreen(properties: List<Property>){
    Scaffold(
        topBar = {
            AppBar(
                currentScreen = Route.PropertiesScreen,
                currentScreenName = stringResource(id = R.string.properties_screen),
                canNavigateBack = false,
                navigateUp = { /*TODO*/ })
        }
    ) {paddingValues ->
        Column {
            LazyColumn(
                contentPadding = paddingValues
            ) {
                items(properties) {
                    PropertyCard(propertyName = it.name, propertyDescription = it.description,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                }
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally), onClick = { /*TODO*/ })
            {
                Text(text = "Thêm sở hữu")
            }
        }

    }
}
@Composable
fun PropertyCard(propertyName: String,
                   propertyDescription: String,
                   modifier: Modifier = Modifier)
{
    Card(
        modifier = modifier
    ){
        Column(
            modifier = Modifier.padding(8.dp)
        ){
            Text(text = propertyName)
            Text(text = propertyDescription)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun Preview(){
    PropertiesScreen(properties = properties)
}