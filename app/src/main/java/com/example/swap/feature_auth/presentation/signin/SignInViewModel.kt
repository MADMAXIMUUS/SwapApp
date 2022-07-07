package com.example.swap.feature_auth.presentation.signin

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swap.core.domain.states.StandardTextFieldState
import com.example.swap.core.presentation.util.UiEvent
import com.example.swap.core.util.Resource
import com.example.swap.core.util.UiText
import com.example.swap.feature_auth.domain.use_cases.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    private val _emailState = mutableStateOf(StandardTextFieldState())
    val emailState: State<StandardTextFieldState> = _emailState

    private val _passwordState = mutableStateOf(StandardTextFieldState())
    val passwordState: State<StandardTextFieldState> = _passwordState

    private val _signInState = mutableStateOf(SignInState())
    val signInState: State<SignInState> = _signInState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.EnteredEmail -> {
                _emailState.value = emailState.value.copy(
                    text = event.email
                )
            }
            is SignInEvent.EnteredPassword -> {
                _passwordState.value = passwordState.value.copy(
                    text = event.password
                )
            }
            is SignInEvent.TogglePasswordVisibility -> {
                _signInState.value = signInState.value.copy(
                    isPasswordVisible = !signInState.value.isPasswordVisible
                )
            }
            is SignInEvent.SignIn -> {
                viewModelScope.launch {
                    _signInState.value = signInState.value.copy(isLoading = true)
                    val loginResult = signInUseCase(
                        email = emailState.value.text,
                        password = passwordState.value.text
                    )
                    _signInState.value = signInState.value.copy(isLoading = false)
                    if (loginResult.emailError != null) {
                        _emailState.value = emailState.value.copy(
                            error = loginResult.emailError
                        )
                    }
                    if (loginResult.passwordError != null) {
                        _passwordState.value = _passwordState.value.copy(
                            error = loginResult.passwordError
                        )
                    }
                    when (loginResult.result) {
                        is Resource.Success -> {
                            _eventFlow.emit(UiEvent.OnLogin)
                        }
                        is Resource.Error -> {
                            _eventFlow.emit(
                                UiEvent.ShowSnackbar(
                                    loginResult.result.uiText ?: UiText.unknownError()
                                )
                            )
                        }
                        null -> {}
                    }
                }
            }
        }
    }
}