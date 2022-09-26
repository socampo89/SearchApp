package com.test.search.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.test.search.databinding.ErrorViewBinding

class ErrorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private var binding : ErrorViewBinding
    init {
        val layoutInflater = LayoutInflater.from(context)
        binding = ErrorViewBinding.inflate(layoutInflater, this, true)
        hide()
    }

    fun setError(error : String){
        binding.errorMessage.text = error
    }

    fun show(){
        binding.root.visibility = VISIBLE
    }

    fun hide(){
        binding.root.visibility = GONE
    }

}