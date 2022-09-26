package com.test.search.data.repository

import com.test.search.domain.repository.SearchRepository
import com.test.search.domain.services.SearchApiService
import com.test.search.presentation.networking.SearchResponse
import com.test.search.presentation.networking.helpers.Resource

class SearchRepositoryImpl(private val apiService: SearchApiService) : SearchRepository() {
    override suspend fun search(query: String?): Resource<SearchResponse> {
        return safeApiCall { apiService.search(query) }
    }
}