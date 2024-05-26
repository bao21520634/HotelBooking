package com.example.hotelbooking.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelbooking.domain.repository.HotelsRepository
import com.example.hotelbooking.util.Event
import com.example.hotelbooking.view.homepage.components.HotelsViewState
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
    private val _state = MutableStateFlow(HotelsViewState())
    val state = _state.asStateFlow()

    fun getTopBookings() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            hotelsRepository.getTopBookings()
                .onRight {hotels ->
                    _state.update {
                        it.copy(hotels = hotels)
                    }
                }
                .onLeft {error ->
                    _state.update {
                        it.copy(error = error.error.message)
                    }
                    sendEvent(Event.Toast(error.error.message))
                }
            _state.update {
                it.copy(isLoading = false)
            }
        }
    }
}