package com.example.swap.presentation.draftscreen.new_advert

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swap.domain.models.Advert
import com.example.swap.domain.use_cases.advert_use_cases.AdvertUseCases
import com.example.swap.objects.Response
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdvertViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val advertUseCases: AdvertUseCases
) : ViewModel() {

    private val _advertData = mutableStateOf<Response<List<Advert>>>(Response.Loading)
    val advertData: State<Response<List<Advert>>> = _advertData

    private val _createAdvertData = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val createAdvertData: State<Response<Boolean>> = _createAdvertData

    private val _updateAdvertData = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val updateAdvert: State<Response<Boolean>> = _updateAdvertData

    fun getAllAdverts() {
        val userid = auth.currentUser?.uid!!
        viewModelScope.launch {
            advertUseCases.getAll(userid).collect {
                _advertData.value = it
            }
        }
    }

    fun createAdvert(
        title: String,
        description: String,
        tags: List<String>,
        authorId: String,
        images: List<String>
    ) {
        viewModelScope.launch {
            advertUseCases.createAdvert(
                title = title,
                description = description,
                tags = tags,
                authorId = authorId,
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
    }
}