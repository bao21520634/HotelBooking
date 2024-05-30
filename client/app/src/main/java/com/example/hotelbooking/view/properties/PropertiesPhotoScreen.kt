package com.example.hotelbooking.view.properties

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hotelbooking.R
import com.example.hotelbooking.navigation.Route
import com.example.hotelbooking.ui.utility.AppBar
import com.example.hotelbooking.ui.utility.ImageWithDeleteButton

@Composable
fun PropertiesPhotoScreen() {
    //image uri
    var imageUriList = remember {
        mutableStateListOf<Uri>()
    }
    //Log.d("Debug1", imageUriList.size.toString())
    //2nd requirement
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents(),
        onResult = { uriList ->
            imageUriList.addAll(uriList)
        }
    )

    Scaffold(
        topBar = {
            AppBar(
                currentScreen = Route.PropertiesSPhotoscreen,
                currentScreenName = stringResource(id = R.string.propertiesPhotos_screen),
                canNavigateBack = false,
                navigateUp = { /*TODO*/ })
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(dimensionResource(id = R.dimen.screenPadding)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.itemInListPadding))
        ){
            items(imageUriList) { index  ->
                ImageWithDeleteButton(
                    uri = index,
                    onDeleteButtonPressed = {  imageUriList.remove(index)}
                )
            }
            item{
                Spacer(Modifier.height(12.dp))
                OutlinedButton(onClick = { galleryLauncher.launch("image/*")},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = colorResource(id = R.color.neutral),
                        containerColor = Color.Transparent
                    )
                ) {
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ){
                        Icon(painter = painterResource(id = R.drawable.baseline_upload_24), contentDescription = null, Modifier.size(56.dp))
                        Text(
                            text = "Thêm ảnh ở đây",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp,
                            color = colorResource(id = R.color.neutral),
                        )
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PropertiesPhotoScreenScreenPreview(){
    PropertiesPhotoScreen()
}
val imageList: List< Int> = listOf(
    R.drawable.hotel_thumbnail,
    R.drawable.koda
)