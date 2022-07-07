package com.example.swap.feature_advert.presentation.create_and_edit_advert

import androidx.lifecycle.ViewModel
import com.example.swap.domain.use_cases.advert_use_cases.AdvertUseCases
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AdvertViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val advertUseCases: AdvertUseCases
) : ViewModel() {

    /*private val _advertsData = mutableStateOf<Response<List<Advert>>>(Response.Loading)
    val advertsData: State<Response<List<Advert>>> = _advertsData

    private val _advertData = mutableStateOf<Response<Advert>>(Response.Loading)
    val advertData: State<Response<Advert>> = _advertData

    private val _createAdvertData = mutableStateOf<Response<Boolean?>>(Response.Success(null))
    val createAdvertData: State<Response<Boolean?>> = _createAdvertData

    private val _updateAdvertData = mutableStateOf<Response<Boolean?>>(Response.Success(null))
    val updateAdvert: State<Response<Boolean?>> = _updateAdvertData

    fun getAllAdverts() {
        val userid = auth.currentUser?.uid
        if (userid != null)
            viewModelScope.launch {
                advertUseCases.getAllWithUserId(userid).collect {
                    _advertsData.value = it
                }
            }
    }

    fun createAdvert(
        title: String,
        description: String,
        tags: List<String>,
        images: List<String>
    ) {
        val id = auth.currentUser?.uid!!
        viewModelScope.launch {
            advertUseCases.createAdvert(
                title = title,
                description = description,
                tags = tags,
                authorId = id,
                images = images
            ).collect {
                _createAdvertData.value = it
            }
        }
    }

    fun updateAdvert(
        advertId: String,
        title: String,
        tags: List<String>,
        images: List<String>,
        description: String
    ) {
        viewModelScope.launch {
            advertUseCases.updateAdvert(advertId, title, tags, images, description)
                .collect { _updateAdvertData.value = it }
        }
    }*/
}