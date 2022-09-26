package com.test.search.domain.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductEntity(
    @SerializedName("id") val id : String?,
    @SerializedName("title") val title : String? = null,
    @SerializedName("price") val price : Double? = null,
    @SerializedName("currency_id") val currencyId : String? = null,
    @SerializedName("permalink") val permalink : String? = null,
    @SerializedName("thumbnail") val thumbnail : String? = null) : Parcelable