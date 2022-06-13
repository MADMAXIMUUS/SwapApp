package com.example.swap.presentation.profilescreen.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swap.domain.models.User
import com.example.swap.domain.use_cases.user_use_cases.UserUserCases
import com.example.swap.objects.Response
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val userUseCases: UserUserCases
) : ViewModel() {

    private val userId = auth.currentUser?.uid

    private val _getUserData = mutableStateOf<Response<User?>>(Response.Success(null))
    val getUserData: State<Response<User?>> = _getUserData

    private val _setUserData = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val setUserData: State<Response<Boolean>> = _setUserData

    fun getUserInfo() {
        if (userId != null) {
            viewModelScope.launch {
                userUseCases.getUserDetails(userId).collect {
                    _getUserData.value = it
                }
            }
        }
    }

    fun setUserInfo(name: String, email: String, phone: String) {
        if (userId != null) {
            viewModelScope.launch {
                userUseCases.setUserDetails(userId, name, email, phone)
                    .collect { _setUserData.value = it }
            }
        }
    }
}