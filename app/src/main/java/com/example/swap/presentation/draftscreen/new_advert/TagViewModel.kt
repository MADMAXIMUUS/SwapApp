package com.example.swap.presentation.draftscreen.new_advert

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swap.domain.models.Tag
import com.example.swap.domain.use_cases.tag_use_cases.TagUseCases
import com.example.swap.objects.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TagViewModel @Inject constructor(
    private val tagUseCases: TagUseCases
) : ViewModel() {

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
    }
}