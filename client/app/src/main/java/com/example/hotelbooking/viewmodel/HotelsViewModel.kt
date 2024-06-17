package com.example.hotelbooking.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelbooking.domain.repository.HotelsRepository
import com.example.hotelbooking.util.Event
import com.example.hotelbooking.view.components.CheckoutViewState
import com.example.hotelbooking.view.components.HotelViewState
import com.example.hotelbooking.view.components.HotelsViewState
import com.example.hotelbooking.view.homepage.components.PlaceViewState
import com.example.hotelbooking.view.homepage.components.SearchViewState
import com.example.hotelbooking.view.properties.components.PropertiesViewState
import com.example.hotelbooking.view.util.sendEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.KProperty

@HiltViewModel
class HotelsViewModel @Inject constructor(
    private val hotelsRepository: HotelsRepository
) : ViewModel() {
    private val _hotelsState = MutableStateFlow(HotelsViewState())
    val hotelsState = _hotelsState.asStateFlow()

    private val _hotelState = MutableStateFlow(HotelViewState())
    val hotelState = _hotelState.asStateFlow()

    private val _placesState = MutableStateFlow(PlaceViewState())
    val placesState = _placesState.asStateFlow()

    private val _searchState = MutableStateFlow(SearchViewState())
    val searchState = _searchState.asStateFlow()

    private val _propertiesState = MutableStateFlow(PropertiesViewState())
    val propertiesState = _propertiesState.asStateFlow()

    private val _nearHotelState = MutableStateFlow(HotelsViewState())
    val nearHotelsState = _nearHotelState.asStateFlow()

    private val _checkoutState = MutableStateFlow(CheckoutViewState())
    val checkoutState = _checkoutState.asStateFlow()

    fun getTopBookings(page: Int = 1) {
        viewModelScope.launch {
            _hotelsState.update {
                it.copy(isLoading = true)
            }
            hotelsRepository.getTopBookings(page)
                .onRight { response ->
                    _hotelsState.update {
                        it.copy(hotels = response.data, page = response.pagination.page)
                    }
                }
                .onLeft { error ->
                    _hotelsState.update {
                        it.copy(error = error.error.message)
                    }
                    sendEvent(Event.Toast(error.error.message))
                }
            _hotelsState.update {
                it.copy(isLoading = false)
            }
        }
    }

    fun getMyFavorites(page: Int = 1) {
        viewModelScope.launch {
            _hotelsState.update {
                it.copy(isLoading = true)
            }
            hotelsRepository.getMyFavorites(page)
                .onRight { response ->
                    _hotelsState.update {
                        it.copy(hotels = response.data, page = response.pagination.page)
                    }
                }
                .onLeft { error ->
                    _hotelsState.update {
                        it.copy(error = error.error.message)
                    }
                    sendEvent(Event.Toast(error.error.message))
                }
            _hotelsState.update {
                it.copy(isLoading = false)
            }
        }
    }

    fun getMyHistory(page: Int = 1) {
        viewModelScope.launch {
            _hotelsState.update {
                it.copy(isLoading = true)
            }
            hotelsRepository.getMyHistory(page)
                .onRight { response ->
                    _hotelsState.update {
                        it.copy(hotels = response.data, page = response.pagination.page)
                    }
                }
                .onLeft { error ->
                    _hotelsState.update {
                        it.copy(error = error.error.message)
                    }
                    sendEvent(Event.Toast(error.error.message))
                }
            _hotelsState.update {
                it.copy(isLoading = false)
            }
        }
    }

    fun getMyBookings(page: Int = 1) {
        viewModelScope.launch {
            _hotelsState.update {
                it.copy(isLoading = true)
            }
            hotelsRepository.getMyBookings(page)
                .onRight { response ->
                    _hotelsState.update {
                        it.copy(hotels = response.data, page = response.pagination.page)
                    }
                }
                .onLeft { error ->
                    _hotelsState.update {
                        it.copy(error = error.error.message)
                    }
                    sendEvent(Event.Toast(error.error.message))
                }
            _hotelsState.update {
                it.copy(isLoading = false)
            }
        }
    }

    fun getHotel(id: String) {
        viewModelScope.launch {
            _hotelState.update {
                it.copy(isLoading = true)
            }
            hotelsRepository.getHotel(id)
                .onRight { hotel ->
                    _hotelState.update {
                        it.copy(hotel = hotel)
                    }
                }
                .onLeft { error ->
                    _hotelState.update {
                        it.copy(error = error.error.message)
                    }
                    sendEvent(Event.Toast(error.error.message))
                }
            _hotelState.update {
                it.copy(isLoading = false)
            }
        }
    }

    fun getLocationPredictions(destination: String) {
        viewModelScope.launch {
            _placesState.update {
                it.copy(isLoading = true)
            }
            delay(500)
            if (destination != "") {
                hotelsRepository.getLocationPredictions(destination)
                    .onRight { places ->
                        _placesState.update {
                            it.copy(predictionPlaces = places)
                        }
                    }
                    .onLeft { error ->
                        _placesState.update {
                            it.copy(error = error.error.message)
                        }
                        sendEvent(Event.Toast(error.error.message))
                    }
            }
            _placesState.update {
                it.copy(isLoading = false)
            }
        }
    }

    fun search(searchParams: Map<String, String>) {
        viewModelScope.launch {
            _hotelsState.update {
                it.copy(isLoading = true)
            }
            hotelsRepository.search(searchParams)
                .onRight { response ->
                    _hotelsState.update {
                        it.copy(hotels = response.data, page = response.pagination.page)
                    }
                }
                .onLeft { error ->
                    _hotelsState.update {
                        it.copy(error = error.error.message)
                    }
                    sendEvent(Event.Toast(error.error.message))
                }
            _hotelsState.update {
                it.copy(isLoading = false)
            }
        }
    }

    fun getNearHotels(lng: String, lat: String) {
        viewModelScope.launch {
            _nearHotelState.update {
                it.copy(isLoading = true)
            }
            hotelsRepository.getNearHotels(lng, lat)
                .onRight { response ->
                    _nearHotelState.update {
                        it.copy(hotels = response.data, page = response.pagination.page)
                    }
                }
                .onLeft { error ->
                    _nearHotelState.update {
                        it.copy(error = error.error.message)
                    }
                    sendEvent(Event.Toast(error.error.message))
                }
            _nearHotelState.update {
                it.copy(isLoading = false)
            }
        }

    }

    fun checkout(
        hotelId: String,
        totalCost: Int,
        checkIn: String,
        checkOut: String,
        adultCount: Int,
        childCount: Int,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        viewModelScope.launch {
            _checkoutState.update {
                it.copy(isLoading = true)
            }
            hotelsRepository.checkout(
                hotelId = hotelId,
                totalCost = totalCost,
                checkIn = checkIn,
                checkOut = checkOut,
                adultCount = adultCount,
                childCount = childCount
            )
                .onRight { response ->
                    _checkoutState.update {
                        it.copy(url = response.url)
                    }
                    onSuccess(response.url)
                }
                .onLeft { error ->
                    _checkoutState.update {
                        it.copy(error = error.error.message)
                    }
                    sendEvent(Event.Toast(error.error.message))
                    onFailure(error.error.message)
                }
            _checkoutState.update {
                it.copy(isLoading = false)
            }
        }
    }

    fun updateSearchParams(
        location_id: String = "",
        location: String = "",
        childCount: Int = 0,
        adultCount: Int = 0,
        roomCount: Int = 0,
        checkIn: String = "",
        checkOut: String = "",
        sortOption: String = "",
        page: String = "1"
    ) {
        viewModelScope.launch {
            val updates = mutableListOf<Pair<KProperty<*>, Any?>>()

            if (location != "") updates.add(SearchViewState::location to location)
            if (location_id != "") updates.add(SearchViewState::location_id to location_id)
            if (childCount != 0) updates.add(SearchViewState::childCount to childCount)
            if (adultCount != 0) updates.add(SearchViewState::adultCount to adultCount)
            if (roomCount != 0) updates.add(SearchViewState::roomCount to roomCount)
            if (checkIn != "") updates.add(SearchViewState::checkIn to checkIn)
            if (checkOut != "") updates.add(SearchViewState::checkOut to checkOut)
            if (sortOption != "") updates.add(SearchViewState::sortOption to sortOption)
            if (page != "") updates.add(SearchViewState::page to page)

            if (updates.isNotEmpty()) {
                _searchState.update { currentState ->
                    var newState = currentState
                    updates.forEach { (property, value) ->
                        newState = when (property.name) {
                            "location" -> newState.copy(location = value as String)
                            "location_id" -> newState.copy(location_id = value as String)
                            "childCount" -> newState.copy(childCount = value as Int)
                            "adultCount" -> newState.copy(adultCount = value as Int)
                            "roomCount" -> newState.copy(roomCount = value as Int)
                            "checkIn" -> newState.copy(checkIn = value as String)
                            "checkOut" -> newState.copy(checkOut = value as String)
                            "sortOption" -> newState.copy(sortOption = value as String)
                            "page" -> newState.copy(page = value as String)
                            else -> newState
                        }
                    }
                    newState
                }
            }
        }
    }

    fun updatePropertiesSearchParams(
        name: String = "",
        quantity: Int = 0,
        address: String = "",
        description: String = "",
        type: String = "",
        adultCount: Int = 0,
        childCount: Int = 0,
        facilities: List<String> = emptyList(),
        bedrooms: List<PropertiesViewState.Bedroom> = emptyList(),
        interior: List<String> = emptyList(),
        pricePerNightWeekdays: Int = 0,
        pricePerNightWeekends: Int = 0,
        starRating: Int = 4,
        imageUrls: List<String> = emptyList(),
    ) {
        viewModelScope.launch {
            val updates = mutableListOf<Pair<KProperty<*>, Any?>>()

            if (name != "") updates.add(PropertiesViewState::name to name)
            if (quantity != 0) updates.add(PropertiesViewState::quantity to quantity)
            if (address != "") updates.add(PropertiesViewState::address to address)
            if (description != "") updates.add(PropertiesViewState::description to description)
            if (type != "") updates.add(PropertiesViewState::type to type)
            if (adultCount != 0) updates.add(PropertiesViewState::adultCount to adultCount)
            if (childCount != 0) updates.add(PropertiesViewState::childCount to childCount)
            if (facilities.isNotEmpty()) updates.add(PropertiesViewState::facilities to facilities)
            if (bedrooms.isNotEmpty()) updates.add(PropertiesViewState::bedrooms to bedrooms)
            if (interior.isNotEmpty()) updates.add(PropertiesViewState::interior to interior)
            if (pricePerNightWeekdays != 0) updates.add(PropertiesViewState::pricePerNightWeekdays to pricePerNightWeekdays)
            if (pricePerNightWeekends != 0) updates.add(PropertiesViewState::pricePerNightWeekends to pricePerNightWeekends)
            if (starRating != 4) updates.add(PropertiesViewState::starRating to starRating)
            if (imageUrls.isNotEmpty()) updates.add(PropertiesViewState::imageUrls to imageUrls)

            if (updates.isNotEmpty()) {
                _propertiesState.update { currentState ->
                    var newState = currentState
                    updates.forEach { (property, value) ->
                        newState = when (property.name) {
                            "name" -> newState.copy(name = value as String)
                            "quantity" -> newState.copy(quantity = value as Int)
                            "address" -> newState.copy(address = value as String)
                            "description" -> newState.copy(description = value as String)
                            "type" -> newState.copy(type = value as String)
                            "adultCount" -> newState.copy(adultCount = value as Int)
                            "childCount" -> newState.copy(childCount = value as Int)
                            "facilities" -> newState.copy(facilities = value as List<String>)
                            "bedrooms" -> newState.copy(bedrooms = value as List<PropertiesViewState.Bedroom>)
                            "interior" -> newState.copy(interior = value as List<String>)
                            "pricePerNightWeekdays" -> newState.copy(pricePerNightWeekdays = value as Int)
                            "pricePerNightWeekends" -> newState.copy(pricePerNightWeekends = value as Int)
                            "starRating" -> newState.copy(starRating = value as Int)
                            "imageUrls" -> newState.copy(imageUrls = value as List<String>)
                            else -> newState
                        }
                    }
                    newState
                }
            }
        }
    }
}