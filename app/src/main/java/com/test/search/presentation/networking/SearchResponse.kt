package com.test.search.presentation.networking

import com.google.gson.annotations.SerializedName
import com.test.search.domain.entity.ProductEntity

data class SearchResponse(
    @SerializedName("query") val query : String?,
    @SerializedName("results")  val results : MutableList<ProductEntity>?
)