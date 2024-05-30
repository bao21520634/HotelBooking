package com.example.hotelbooking.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelbooking.domain.repository.UserRepository
import com.example.hotelbooking.util.Event
import com.example.hotelbooking.view.components.ProfileViewState
import com.example.hotelbooking.view.util.sendEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _state = MutableStateFlow(ProfileViewState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getUser()
        }
    }

    fun getUser() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            userRepository.getUser()
                .onRight { user ->
                    _state.update {
                        it.copy(user = user)
                    }
                }
                .onLeft { error ->
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

    fun favorite(hotelId: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            userRepository.favorite(hotelId)
                .onRight { user ->
                    _state.update {
                        it.copy(user = user)
                    }
                }
                .onLeft { error ->
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