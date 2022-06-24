package com.example.swap.presentation.profilescreen.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swap.domain.use_cases.auth_use_cases.AuthenticationUseCases
import com.example.swap.objects.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val authUseCases: AuthenticationUseCases
) : ViewModel() {
    val isUserAuthenticated get() = authUseCases.isUserAuthenticated

    private val _logInState = mutableStateOf<Response<Boolean?>>(Response.Success(null))
    val logInState: State<Response<Boolean?>> = _logInState

    private val _signInState = mutableStateOf<Response<Boolean?>>(Response.Success(null))
    val signInState: State<Response<Boolean?>> = _signInState

    private val _logOutState = mutableStateOf<Response<Boolean?>>(Response.Success(null))
    val logOutState: State<Response<Boolean?>> = _logOutState

    private val _firebaseAuthState = mutableStateOf(false)
    val firebaseAuthState: State<Boolean> = _firebaseAuthState

    fun logIn(email: String, password: String) {
        viewModelScope.launch {
            authUseCases.firebaseLogIn(email, password).collect {
                _logInState.value = it
            }
        }
    }

    fun signInEmail(name: String, email: String, password: String) {
        viewModelScope.launch {
            authUseCases.firebaseSignInEmail(name, email, password).collect {
                _signInState.value = it
            }
        }
    }

    fun logOut() {
        viewModelScope.launch {
            authUseCases.firebaseLogOut().collect {
                _logOutState.value = it
                if (it == Response.Success(true)) {
                    _signInState.value = Response.Success(false)
                }
            }
        }
    }

    fun getFirebaseAuthState() {
        viewModelScope.launch {
            authUseCases.firebaseAuthState().collect {
                _firebaseAuthState.value = it
            }
        }
    }
}