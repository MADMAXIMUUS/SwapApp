package com.example.swap.ui.layout.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swap.common.EventHandler
import com.example.swap.domain.use_cases.auth_use_cases.AuthenticationUseCases
import com.example.swap.objects.Response
import com.example.swap.ui.layout.login.models.LoginEvent
import com.example.swap.ui.layout.login.models.LoginSubState
import com.example.swap.ui.layout.login.models.LoginViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCases: AuthenticationUseCases
) : ViewModel(), EventHandler<LoginEvent> {
    val isUserAuthenticated get() = authUseCases.isUserAuthenticated

    private val _viewState = MutableLiveData(LoginViewState())
    val viewState: LiveData<LoginViewState> = _viewState

    override fun obtainEvent(event: LoginEvent) {
        when (event) {
            LoginEvent.ActionClicked -> switchActionState()
            is LoginEvent.NameChanged -> nameChanged(event.value)
            is LoginEvent.EmailChanged -> emailChanged(event.value)
            is LoginEvent.PasswordChanged -> passwordChanged(event.value)
        }
    }

    private fun nameChanged(value: String) {
        if (value != _viewState.value?.nameValue)
            _viewState.postValue(_viewState.value?.copy(nameValue = value))
    }

    private fun emailChanged(value: String) {
        if (value != _viewState.value?.emailValue)
            _viewState.postValue(_viewState.value?.copy(emailValue = value))
    }

    private fun passwordChanged(value: String) {
        if (value != _viewState.value?.passwordValue)
            _viewState.postValue(_viewState.value?.copy(passwordValue = value))
    }

    private fun switchActionState() {
        when (_viewState.value?.loginSubState) {
            LoginSubState.SignIn -> _viewState.postValue(_viewState.value?.copy(loginSubState = LoginSubState.SignUp))
            LoginSubState.SignUp -> _viewState.postValue(_viewState.value?.copy(loginSubState = LoginSubState.SignIn))
            else -> Unit
        }
    }

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