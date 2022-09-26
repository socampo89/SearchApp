package com.test.search.domain.repository

import com.test.search.presentation.networking.SearchResponse
import com.test.search.presentation.networking.helpers.Resource

abstract class SearchRepository : BaseRepository(){
    abstract suspend fun search(query : String?): Resource<SearchResponse>
}