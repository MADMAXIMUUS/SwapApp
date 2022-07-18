package com.example.swap.layout.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swap.common.EventHandler
import com.example.swap.core.domain.models.User
import com.example.swap.domain.use_cases.user_use_cases.UserUseCases
import com.example.swap.layout.profile.models.ProfileEvent
import com.example.swap.layout.profile.models.ProfileViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel(), EventHandler<ProfileEvent> {

    private val _profileViewState: MutableStateFlow<ProfileViewState> =
        MutableStateFlow(ProfileViewState.Loading)
    val profileViewState: StateFlow<ProfileViewState> = _profileViewState

    override fun obtainEvent(event: ProfileEvent) {
        when (val currentState = _profileViewState.value) {
            is ProfileViewState.Loading -> reduce(event, currentState)
            is ProfileViewState.Display -> reduce(event, currentState)
            is ProfileViewState.Error -> reduce(event, currentState)
            is ProfileViewState.NoLogin -> reduce(event, currentState)
        }
    }

    private fun reduce(event: ProfileEvent, currentState: ProfileViewState.Loading) {
        when (event) {
            is ProfileEvent.EnterScreen -> fetchProfile(event.userId)
        }
    }

    private fun reduce(event: ProfileEvent, currentState: ProfileViewState.NoLogin) {
        when (event) {
            //ProfileEvent.ReloadScreen -> fetchProfile()
            //is ProfileEvent.EnterScreen -> fetchProfile(event.userId)
        }
    }

    private fun reduce(event: ProfileEvent, currentState: ProfileViewState.Display) {
        when (event) {
            is ProfileEvent.EnterScreen -> fetchProfile(event.userId)
            is ProfileEvent.OnAdvertClicked -> performAdvertClicked()
        }
    }

    private fun reduce(event: ProfileEvent, currentState: ProfileViewState.Error) {
        when (event) {
            //ProfileEvent.ReloadScreen -> fetchProfile(needToReload = true)
        }
    }

    private fun fetchProfile(userId: String) {
        try {
            var user = User()
            viewModelScope.launch {
                userUseCases.getUserDetails(userId).collect { userInfo ->
                    /*when (userInfo) {
                        is Resource.Error<User> -> {
                            _profileViewState.compareAndSet(
                                _profileViewState.value,
                                ProfileViewState.Error
                            )
                        }
                        is Resource.Loading<User> -> {
                            _profileViewState.compareAndSet(
                                _profileViewState.value,
                                ProfileViewState.Loading
                            )
                        }
                        is Resource.Success<User> -> {
                            user = userInfo.data!!
                        }
                    }*/
                }
            }


                /*val cardItems = mutableListOf<AdvertCardItem>()
                viewModelScope.launch {
                    /*advertUseCases.getAllAdvertsFromUser(user.id).collect { adverts ->
                        when (adverts) {
                            is Resource.Error<List<Advert>> -> _profileViewState.compareAndSet(
                                _profileViewState.value,
                                ProfileViewState.Error
                            )
                            is Resource.Loading<List<Advert>> -> _profileViewState.compareAndSet(
                                _profileViewState.value,
                                ProfileViewState.Loading
                            )
                            is Resource.Success<List<Advert>> -> {
                                val advertList = adverts.data
                                advertList?.forEach { advert ->
                                    val advertCardItem = AdvertCardItem(
                                        id = advert.id,
                                        address = advert.address,
                                        imageUrl = advert.imageListUrls[0],
                                        tags = advert.tags,
                                        title = advert.title
                                    )
                                    cardItems.add(advertCardItem)
                                }
                            }
                        }
                    }*/
                }

                _profileViewState.compareAndSet(
                    _profileViewState.value,
                    ProfileViewState.Display(
                        items = cardItems,
                        id = user.id,
                        name = user.name,
                        imageUrl = user.avatarUrl,
                        rating = user.rating.toString()
                    )
                )
            }*/
        } catch (e: Exception) {
            _profileViewState.compareAndSet(_profileViewState.value, ProfileViewState.Error)
        }
    }

    private fun performAdvertClicked() {

    }
}