package com.test.search.domain.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductEntity(
    @SerializedName("id") val id : String?,
    @SerializedName("title") val title : String?,
    @SerializedName("price") val price : Double?,
    @SerializedName("currency_id") val currencyId : String?,
    @SerializedName("permalink") val permalink : String?,
    @SerializedName("thumbnail") val thumbnail : String?) : Parcelable