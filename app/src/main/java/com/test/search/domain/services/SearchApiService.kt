package com.test.search.domain.services

import com.test.search.presentation.networking.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiService {
    @GET("sites/MLA/search")
    suspend fun search(@Query("q") query : String?) : Response<SearchResponse>
}