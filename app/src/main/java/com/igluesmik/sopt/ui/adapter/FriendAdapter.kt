package com.igluesmik.sopt.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.igluesmik.sopt.data.model.domain.Friend
import com.igluesmik.sopt.databinding.ItemFriendBinding

class FriendAdapter: RecyclerView.Adapter<FriendAdapter.ViewHolder>() {

    private var _data = mutableListOf<Friend>()
    var data : List<Friend> = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }

    inner class ViewHolder(private val binding: ItemFriendBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Friend) {
            binding.friend = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemFriendBinding = ItemFriendBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(_data[position])
    }

    override fun getItemCount(): Int {
        return _data.size
    }

}