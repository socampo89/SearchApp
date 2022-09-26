package com.test.search.presentation.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.test.search.databinding.ProductItemBinding
import com.test.search.domain.entity.ProductEntity
import com.test.search.presentation.views.helper.BaseAdapter

class ResultsAdapter(items : MutableList<ProductEntity>,val  onItemClicked : (ProductEntity) -> Unit) : BaseAdapter<ProductEntity, ProductItemBinding>(items) {
    override fun bindingInflater(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        attach: Boolean
    ): ProductItemBinding {
        return ProductItemBinding.inflate(inflater, parent, attach)
    }

    override fun bind(item: ProductEntity, binding: ProductItemBinding) {
        item.thumbnail?.let {
            Glide.with(binding.root).load(it).into(binding.imageViewProduct)
        }
        binding.textViewProductName.text = item.title
        val price = item.currencyId + item.price
        binding.textViewProductPrice.text = price
        binding.root.setOnClickListener{
            onItemClicked.invoke(item)
        }
    }
}