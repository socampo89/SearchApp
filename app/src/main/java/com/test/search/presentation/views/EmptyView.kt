package com.test.search.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.test.search.databinding.EmptyViewBinding

class EmptyView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private var binding : EmptyViewBinding
    init {
        val layoutInflater = LayoutInflater.from(context)
        binding = EmptyViewBinding.inflate(layoutInflater, this, true)
        hide()
    }

    fun show(){
        binding.root.visibility = VISIBLE
    }

    fun hide(){
        binding.root.visibility = GONE
    }

}