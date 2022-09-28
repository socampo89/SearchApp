package com.test.search.domain.repository

import com.test.search.domain.entity.SearchEntity
import com.test.search.presentation.networking.helpers.Resource

interface SearchRepository{
    suspend fun search(query : String?): Resource<SearchEntity>
}