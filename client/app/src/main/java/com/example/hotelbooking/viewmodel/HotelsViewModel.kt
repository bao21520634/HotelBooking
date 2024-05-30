package com.example.hotelbooking.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelbooking.domain.repository.HotelsRepository
import com.example.hotelbooking.util.Event
import com.example.hotelbooking.view.components.HotelViewState
import com.example.hotelbooking.view.components.HotelsViewState
import com.example.hotelbooking.view.util.sendEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HotelsViewModel @Inject constructor(
    private val hotelsRepository: HotelsRepository
): ViewModel() {
    private val _hotelsState = MutableStateFlow(HotelsViewState())
    val hotelsState = _hotelsState.asStateFlow()

    private val _hotelState = MutableStateFlow(HotelViewState())
    val hotelState = _hotelState.asStateFlow()

    fun getTopBookings(page: Int = 1) {
        viewModelScope.launch {
            _hotelsState.update {
                it.copy(isLoading = true)
            }
            hotelsRepository.getTopBookings(page)
                .onRight {response ->
                    _hotelsState.update {
                        it.copy(hotels = response.data, page = response.pagination.page)
                    }
                }
                .onLeft {error ->
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
                .onRight {response ->
                    _hotelsState.update {
                        it.copy(hotels = response.data, page = response.pagination.page)
                    }
                }
                .onLeft {error ->
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
                .onRight {response ->
                    _hotelsState.update {
                        it.copy(hotels = response.data, page = response.pagination.page)
                    }
                }
                .onLeft {error ->
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
                .onRight {response ->
                    _hotelsState.update {
                        it.copy(hotels = response.data, page = response.pagination.page)
                    }
                }
                .onLeft {error ->
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
                .onRight {hotel ->
                    _hotelState.update {
                        it.copy(hotel = hotel)
                    }
                }
                .onLeft {error ->
                    _hotelState.update {
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