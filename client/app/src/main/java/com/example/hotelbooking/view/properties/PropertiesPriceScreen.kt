package com.example.hotelbooking.view.properties

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hotelbooking.R
import com.example.hotelbooking.navigation.Route
import com.example.hotelbooking.ui.utility.AppBar
import com.example.hotelbooking.ui.utility.InfoTextField
import com.example.hotelbooking.viewmodel.HotelsViewModel
import com.example.hotelbooking.viewmodel.UsersViewModel

@Composable
fun PropertiesPriceScreen(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    hotelsViewModel: HotelsViewModel = hiltViewModel(),
    usersViewModel: UsersViewModel = hiltViewModel(),
) {
    val propertiesState by hotelsViewModel.propertiesState.collectAsStateWithLifecycle()

    var pricePerNightWeekdays: Int by remember { mutableStateOf(propertiesState.pricePerNightWeekdays) }
    var pricePerNightWeekends: Int by remember { mutableStateOf(propertiesState.pricePerNightWeekends) }
    Scaffold(
        modifier = Modifier.padding(bottom = 80.dp),
        topBar = {
            AppBar(
                currentScreen = Route.PropertiesPriceScreen,
                currentScreenName = stringResource(id = R.string.propertiesPrice_screen),
                canNavigateBack = false,
                navigateUp = { /*TODO*/ })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(dimensionResource(R.dimen.screenPadding))
        ) {
            Column {
                CommonHeaderText(text = "Giá/đêm( ngày thường)")
                PriceRow(
                    price = pricePerNightWeekdays,
                    onPriceChange = { pricePerNightWeekdays = it.toInt() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
            Column {
                CommonHeaderText(text = "Giá/đêm( cuối tuần)")
                PriceRow(
                    price = pricePerNightWeekends,
                    onPriceChange = { pricePerNightWeekends = it.toInt() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }
    }

    hotelsViewModel.updatePropertiesSearchParams(
        pricePerNightWeekdays = pricePerNightWeekdays,
        pricePerNightWeekends = pricePerNightWeekends
    )
}

@Composable
fun PriceRow(price: Int, onPriceChange: (String) -> (Unit), modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(Modifier.width(8.dp))
        InfoTextField(
            value = price.toString(),
            promptText = "VNĐ",
            onValueChange = onPriceChange,
        )
    }
}