package com.example.swap.feature_auth.presentation.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swap.core.presentation.util.UiEvent
import com.example.swap.core.util.Resource
import com.example.swap.core.util.Screen
import com.example.swap.feature_auth.domain.use_cases.IsUserAuthenticated
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authUseCase: IsUserAuthenticated
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<UiEvent>(replay = 2)
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            when(authUseCase()) {
                is Resource.Success -> {
                    _eventFlow.emit(
                        UiEvent.Navigate(Screen.HomeScreen.route)
                    )
                }
                is Resource.Error -> {
                    _eventFlow.emit(
                        UiEvent.Navigate(Screen.SignUpScreen.route)
                    )
                }
            }
        }
    }
}