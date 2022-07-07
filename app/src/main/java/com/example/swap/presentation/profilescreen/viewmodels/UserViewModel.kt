package com.example.swap.presentation.profilescreen.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swap.domain.use_cases.user_use_cases.UserUseCases
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val userUseCases: UserUseCases
) : ViewModel() {

    fun getUserInfo(userId: String) {
        viewModelScope.launch {
            userUseCases.getUserDetails(userId).collect {
                //_getUserData.value = it
            }
        }
    }

    fun setUserInfo(userId: String, name: String, email: String, phone: String) {
        viewModelScope.launch {
            userUseCases.setUserDetails(userId, name, email, phone).collect {
                //_setUserData.value = it
            }
        }
    }
}