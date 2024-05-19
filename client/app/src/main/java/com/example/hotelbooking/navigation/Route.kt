package com.example.hotelbooking.navigation

import androidx.annotation.StringRes
import com.example.hotelbooking.R

sealed class Route(
    val route: String,
    @StringRes val resourceId: Int
) {
    object OnBoardingScreen : Route(route = "onBoardingScreen", R.string.onboarding_screen)

    object HomeScreen : Route(route = "homeScreen", R.string.homepage_screen)
    object HomeResultScreen : Route(route = "homeResultScreen", R.string.homepage_screen)
    object HomeDateScreen : Route(route = "homeDateScreen", R.string.homepageDate_screen)
    object HomeRoomScreen : Route(route = "homeRoomScreen", R.string.homepageRoom_screen)

    object FavoriteScreen : Route(route = "favoriteScreen", R.string.favorite_screen)

    object MyBookingsScreen : Route(route = "myBookingsScreen", R.string.my_bookings_screen)

    object DetailsScreen : Route(route = "detailsScreen", R.string.details_screen)

    object ProfileScreen : Route(route = "profileScreen", R.string.profile_screen)

    object PropertiesScreen : Route(route = "propertiesScreen", R.string.properties_screen)
    object PropertiesInformationScreen : Route(route = "propertiesInformationScreen", R.string.propertiesInformation_screen)
    object PropertiesDetailsScreen : Route(route = "propertiesDetailsScreen", R.string.propertiesDetails_screen)
    object PropertiesPriceScreen : Route(route = "propertiesPriceScreen", R.string.propertiesPrice_screen)
    object PropertiesSPhotoscreen : Route(route = "propertiesSPhotoscreen", R.string.propertiesPhotos_screen)

    object AuthRoute : Route(route = "Auth", R.string.Auth )
    object LogInScreen: Route(route = "logInScreen", R.string.login_screen)
    object SignUpScreen: Route(route = "signUpScreen", R.string.signUp_screen)
    object ForgotPassword: Route(route = "forgotPasswordScreen", R.string.forgotPassword_screen)


}