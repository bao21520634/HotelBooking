package com.example.hotelbooking.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelbooking.domain.model.Message
import com.example.hotelbooking.domain.repository.AuthRepository
import com.example.hotelbooking.util.Event
import com.example.hotelbooking.view.auth.components.AuthViewState
import com.example.hotelbooking.view.util.sendEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {
    private val _state = MutableStateFlow(AuthViewState())
    val state = _state.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            authRepository.login(email, password)
                .onRight {message ->
                    _state.update {
                        it.copy(message = message.body())
                    }
                }
                .onLeft {error ->
                    _state.update {
                        it.copy(error = error.error.message)
                    }
                    Log.d("", error.t.toString())
                    sendEvent(Event.Toast(error.t.toString()))
                }
            _state.update {
                it.copy(isLoading = false)
            }
        }
    }

    fun register(email: String, username: String, password: String, confirmPassword: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            authRepository.register(email, username, password, confirmPassword)
                .onRight {message ->
                    _state.update {
                        it.copy(message = message.body())
                    }
                }
                .onLeft {error ->
                    _state.update {
                        it.copy(error = error.error.message)
                    }
                    Log.d("", error.t.toString())
                    sendEvent(Event.Toast(error.t.toString()))
                }
            _state.update {
                it.copy(isLoading = false)
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            authRepository.logout()
                .onRight {message ->
                    _state.update {
                        it.copy(message = message.body())
                    }
                }
                .onLeft {error ->
                    _state.update {
                        it.copy(error = error.error.message)
                    }
                    Log.d("", error.t.toString())
                    sendEvent(Event.Toast(error.t.toString()))
                }
            _state.update {
                it.copy(isLoading = false)
            }
        }
    }
}