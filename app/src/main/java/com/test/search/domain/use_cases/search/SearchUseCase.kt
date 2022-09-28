package com.test.search.domain.use_cases.search

import com.test.search.domain.entity.SearchEntity
import com.test.search.domain.repository.SearchRepository
import com.test.search.domain.use_cases.BaseUseCase
import com.test.search.presentation.networking.helpers.Resource

class SearchUseCase(private val repository: SearchRepository) :
    BaseUseCase<SearchEntity, String>() {
    override suspend fun invoke(params: String?): Resource<SearchEntity> {
        return repository.search(params)
    }
}