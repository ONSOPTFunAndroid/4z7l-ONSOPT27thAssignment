package com.igluesmik.sopt.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.igluesmik.sopt.data.model.entity.Friend
import com.igluesmik.sopt.databinding.ItemFriendBinding
import java.util.zip.Inflater

class FriendAdapter: RecyclerView.Adapter<FriendAdapter.ViewHolder>() {

    private var _items = mutableListOf<Friend>()
    var items : List<Friend>
        get() = _items
        set(value) {
            _items.clear()
            _items.addAll(value)
            notifyDataSetChanged()
        }

    inner class ViewHolder(private val binding: ItemFriendBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Friend) {
            binding.firstName.text = item.first_name
            binding.lastName.text = item.last_name
            Glide.with(binding.root)
                .load(item.avatar)
                .into(binding.avatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemFriendBinding = ItemFriendBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(_items[position])
    }

    override fun getItemCount(): Int {
        return _items.size
    }

}