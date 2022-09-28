package com.test.search.data.repository

import com.test.search.domain.entity.SearchEntity
import com.test.search.domain.repository.SearchRepository
import com.test.search.domain.services.SearchApiService
import com.test.search.presentation.networking.helpers.BaseApi
import com.test.search.presentation.networking.helpers.Resource

class SearchRepositoryImpl(private val baseApi : BaseApi, private val apiService: SearchApiService) : SearchRepository {
    override suspend fun search(query: String?): Resource<SearchEntity> {
        val response = baseApi.safeApiCall { apiService.search(query) }
        return if(response is Resource.Success){
            Resource.Success(response.data?.toSearchEntity() ?: SearchEntity("", mutableListOf()))
        } else {
            Resource.Error(response.message ?: "")
        }
    }
}