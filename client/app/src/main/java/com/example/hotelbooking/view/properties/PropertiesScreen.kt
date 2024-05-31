package com.example.hotelbooking.view.properties

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hotelbooking.R
import com.example.hotelbooking.navigation.Route
import com.example.hotelbooking.domain.model.Property
import com.example.hotelbooking.ui.utility.AppBar
import com.example.hotelbooking.view.components.HotelViewState
import com.example.hotelbooking.view.components.HotelsViewState
import com.example.hotelbooking.view.components.ProfileViewState
import com.example.hotelbooking.viewmodel.HotelsViewModel
import com.example.hotelbooking.viewmodel.UsersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun PropertiesScreen(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    hotelsViewModel: HotelsViewModel = hiltViewModel(),
    usersViewModel: UsersViewModel = hiltViewModel(),
    onCreate: () -> Unit
) {
    val hotelsState by hotelsViewModel.hotelsState.collectAsStateWithLifecycle()

    val userState by usersViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        hotelsViewModel.getTopBookings()
        usersViewModel.getUser()
    }

    PropertiesContent(hotelsState = hotelsState, userState = userState, onCreate = onCreate)
}

@Composable
fun PropertiesContent(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    hotelsState: HotelsViewState,
    userState: ProfileViewState,
    hotelsViewModel: HotelsViewModel = hiltViewModel(),
    usersViewModel: UsersViewModel = hiltViewModel(),
    onCreate: () -> Unit
){
    Scaffold(
        modifier = Modifier.padding(bottom = 80.dp),
        topBar = {
            AppBar(
                currentScreen = Route.PropertiesScreen,
                currentScreenName = stringResource(id = R.string.properties_screen),
                canNavigateBack = false,
                navigateUp = { /*TODO*/ }
            )
        }
    ) {paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(dimensionResource(id = R.dimen.screenPadding)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.itemInListPadding))
        ) {
//            items(properties) {
//                PropertyCard(propertyName = it.name, propertyDescription = it.description,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                )
//            }

            item{
                Spacer(Modifier.height(4.dp))
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onCreate,
                    shape = RoundedCornerShape(8.dp)
                )
                {
                    Text(
                        text = "Thêm sở hữu",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = colorResource(id = R.color.neutral),
                        modifier = Modifier.padding(16.dp)
                    )
                }
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
        modifier = modifier,
        border = BorderStroke(1.dp, Color.Gray),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,

        )
    ){
        Column(
            modifier = Modifier.padding(start = 16.dp, top = 12.dp, bottom = 16.dp),
        ){
            Text(text = propertyName,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
            Spacer(Modifier.height(8.dp))
            Text(text = propertyDescription,
                color = Color.DarkGray,
                fontSize = 15.sp
            )
        }

    }
}