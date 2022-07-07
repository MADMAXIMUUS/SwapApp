package com.example.swap.feature_advert.presentation.create_and_edit_advert

import androidx.lifecycle.ViewModel
import com.example.swap.feature_advert.domain.use_case.tag.TagUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TagViewModel @Inject constructor(
    private val tagUseCases: TagUseCases
) : ViewModel() {
/*
    private val _tagsData = mutableStateOf<Response<List<Tag>>>(Response.Loading)
    val tagsData: State<Response<List<Tag>>> = _tagsData

    private val _addTagData = mutableStateOf<Response<Boolean?>>(Response.Success(null))
    val addTagData: State<Response<Boolean?>> = _addTagData

    fun getAllTags() {
        viewModelScope.launch {
            tagUseCases.getAllTags().collect {
                _tagsData.value = it
            }
        }
    }

    fun addTag(
        text: String
    ) {
        viewModelScope.launch {
            tagUseCases.addTag(
                text = text
            ).collect {
                _addTagData.value = it
            }
        }
    }*/
}