package com.test.search.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductEntity(
    val id : String?,
    val title : String? = null,
    val price : Double? = null,
    val currencyId : String? = null,
    val permalink : String? = null,
    val availableQuantity : Int? = null,
    val soldQuantity : Int? = null,
    val thumbnail : String? = null) : Parcelable