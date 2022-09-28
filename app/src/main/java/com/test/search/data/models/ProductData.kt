package com.test.search.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.test.search.domain.entity.ProductEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductData(
    @SerializedName("id") val id : String?,
    @SerializedName("title") val title : String? = null,
    @SerializedName("price") val price : Double? = null,
    @SerializedName("currency_id") val currencyId : String? = null,
    @SerializedName("permalink") val permalink : String? = null,
    @SerializedName("available_quantity") val availableQuantity : Int? = null,
    @SerializedName("sold_quantity") val soldQuantity : Int? = null,
    @SerializedName("thumbnail") val thumbnail : String? = null) : Parcelable {
    fun toProductEntity()  : ProductEntity{
        return ProductEntity(id = id, title = title,
            price = price, currencyId = currencyId,
            permalink = permalink, availableQuantity = availableQuantity, soldQuantity = soldQuantity,
            thumbnail = thumbnail)
    }
}