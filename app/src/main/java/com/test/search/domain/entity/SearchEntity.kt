package com.test.search.domain.entity

data class SearchEntity(
    val query : String?,
    val results : MutableList<ProductEntity>
)