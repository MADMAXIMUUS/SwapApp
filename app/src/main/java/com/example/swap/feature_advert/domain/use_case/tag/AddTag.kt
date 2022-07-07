package com.example.swap.feature_advert.domain.use_case.tag

import com.example.swap.domain.repositories.TagRepository
import javax.inject.Inject

class AddTag @Inject constructor(
    private val repository: TagRepository
) {
    suspend operator fun invoke(text: String) = repository.addTag(text)
}