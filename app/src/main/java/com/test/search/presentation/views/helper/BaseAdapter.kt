package com.test.search.presentation.views.helper

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

@Suppress("UNCHECKED_CAST")
@SuppressLint("NotifyDataSetChanged")
abstract class BaseAdapter<T, U : ViewBinding>(var items: MutableList<T>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    abstract fun bindingInflater(inflater : LayoutInflater, parent : ViewGroup?, attach : Boolean) : U

    abstract fun bind(item: T, binding : U)

    fun update(items: MutableList<T>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = bindingInflater(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        bind(items[position], (holder as ViewHolder).binding as U)
    }
}

class ViewHolder(viewBinding: ViewBinding): RecyclerView.ViewHolder(viewBinding.root){
    var binding = viewBinding
}