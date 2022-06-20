package com.example.swap.domain.use_cases.tag_use_cases

import com.example.swap.domain.repositories.TagRepository
import javax.inject.Inject

class AddTag @Inject constructor(
    private val repository: TagRepository
) {
    operator fun invoke(text: String) = repository.addTag(text)
}