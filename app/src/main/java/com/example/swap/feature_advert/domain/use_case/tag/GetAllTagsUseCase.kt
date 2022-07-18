package com.example.swap.feature_advert.domain.use_case.tag

import com.example.swap.domain.repositories.TagRepository
import javax.inject.Inject

class GetAllTagsUseCase @Inject constructor(
    private val repository: TagRepository
) {
    operator fun invoke() = repository.getAllTags()
}