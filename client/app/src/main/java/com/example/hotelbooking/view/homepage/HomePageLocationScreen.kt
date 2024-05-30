package com.example.hotelbooking.view.homepage

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hotelbooking.R
import com.example.hotelbooking.navigation.Route
import com.example.hotelbooking.ui.utility.AppBar
import com.example.hotelbooking.ui.utility.InfoTextField

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun HomePageLocationScreen(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
) {
    var location: String by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppBar(
                currentScreen = Route.HomeRoomScreen,
                currentScreenName = "",
                canNavigateBack = true,
                navigateUp = { /*TODO*/ }
            )
        }
    ) { paddingValue ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)
                .padding(dimensionResource(id = R.dimen.screenPadding)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.columnPadding))
        ) {
            InfoTextField(
                value = location,
                onValueChange = { location = it },
                promptText = "Nơi bạn muốn tìm",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(48.dp),
            )
            LazyColumn {
                items(
                    listOf(
                        "Thu Duc, TP Ho Chi Minh",
                        "Thu Duc, TP Ho Chi Minh",
                        "Thu Duc, TP Ho Chi Minh"
                    )
                ) { item ->
                    SearchResult(text = item, onClick = { })
                }
            }
        }
    }
}

@Composable
fun SearchResult(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.outline_location_on_24),
                contentDescription = "Location",
                tint = colorResource(id = R.color.primary)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = text, fontSize = 14.sp)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
internal fun HomePageLocationScreenPreview() {
    HomePageLocationScreen()
}