package com.test.search.data.models

import com.google.gson.annotations.SerializedName
import com.test.search.data.models.ProductData
import com.test.search.domain.entity.ProductEntity
import com.test.search.domain.entity.SearchEntity

data class SearchResponse(
    @SerializedName("query") val query : String?,
    @SerializedName("results")  val results : MutableList<ProductData>?
) {
    fun toSearchEntity() : SearchEntity{
        return SearchEntity(query, results?.map {
            it.toProductEntity()
        }?.toMutableList() ?: mutableListOf())
    }
}