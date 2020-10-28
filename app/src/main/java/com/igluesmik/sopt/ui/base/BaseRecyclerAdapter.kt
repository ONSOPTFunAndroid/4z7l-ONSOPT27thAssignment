package com.igluesmik.sopt.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BaseRecyclerAdapter<T : Any, R : ViewDataBinding>(
    @LayoutRes val layoutResId: Int
) :
    RecyclerView.Adapter<BaseRecyclerAdapter.BaseViewHolder>() {

    val items = mutableListOf<T>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = DataBindingUtil.inflate<R>(
            LayoutInflater.from(parent.context), layoutResId, parent, false
        )
        return BaseViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class BaseViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Any) {
            binding.executePendingBindings()
        }
    }

    fun updateItems(updateItems: List<T>) {
        items.run {
            clear()
            addAll(updateItems)
        }
        notifyDataSetChanged()
    }

    fun clearItems() {
        items.clear()
        notifyDataSetChanged()
    }

}