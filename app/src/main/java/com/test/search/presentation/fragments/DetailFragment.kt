package com.test.search.presentation.fragments

import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.test.search.R
import com.test.search.databinding.FragmentDetailBinding
import com.test.search.domain.entity.ProductEntity


class DetailFragment : Fragment() {

    private lateinit var binding : FragmentDetailBinding
    private lateinit var productEntity: ProductEntity

    companion object{
        const val PRODUCT_ENTITY_ARG = "PRODUCT_ENTITY_ARG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initArguments()
    }

    private fun initArguments() {
        productEntity = requireArguments().getParcelable(PRODUCT_ENTITY_ARG)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        productEntity.thumbnail?.let {
            Glide.with(this).load(it).into(binding.imageViewProduct)
        }
        binding.textViewProductName.text = productEntity.title
        val price = productEntity.currencyId + productEntity.price
        binding.textViewProductPrice.text = price
        binding.textViewProductPermalink.apply {
            text = productEntity.permalink
            paintFlags =paintFlags or Paint.UNDERLINE_TEXT_FLAG
            movementMethod = LinkMovementMethod.getInstance()
            setOnClickListener {
                productEntity.permalink?.let { link -> openLink(link) }
            }
        }
        binding.textViewQuantitySold.text = productEntity.soldQuantity.toString()
        binding.textViewQuantityAvailable.text = productEntity.availableQuantity.toString()
    }

    private fun openLink(url : String){
        try {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(browserIntent)
        }catch (_ : Exception){
            Toast.makeText(requireContext(), getString(R.string.error_message_default), Toast.LENGTH_SHORT).show()
        }
    }
}