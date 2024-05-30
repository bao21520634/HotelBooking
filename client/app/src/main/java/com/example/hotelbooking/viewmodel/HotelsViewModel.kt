package com.example.hotelbooking.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelbooking.domain.repository.HotelsRepository
import com.example.hotelbooking.util.Event
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

            if (location!= "") updates.add(SearchViewState::location to location)
            if (location_id!= "") updates.add(SearchViewState::location_id to location_id)
            if (childCount!= 0) updates.add(SearchViewState::childCount to childCount)
            if (adultCount!= 0) updates.add(SearchViewState::adultCount to adultCount)
            if (roomCount!= 0) updates.add(SearchViewState::roomCount to roomCount)
            if (checkIn!= "") updates.add(SearchViewState::checkIn to checkIn)
            if (checkOut!= "") updates.add(SearchViewState::checkOut to checkOut)
            if (sortOption!= "") updates.add(SearchViewState::sortOption to sortOption)
            if (page!= "") updates.add(SearchViewState::page to page)

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
}