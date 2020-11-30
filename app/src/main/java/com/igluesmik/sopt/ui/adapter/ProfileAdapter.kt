package com.igluesmik.sopt.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.igluesmik.sopt.R
import com.igluesmik.sopt.data.model.domain.Friend
import com.igluesmik.sopt.data.model.entity.Profile
import com.igluesmik.sopt.databinding.ItemProfileBinding
import com.igluesmik.sopt.databinding.ItemProfileGridBinding
import com.igluesmik.sopt.ui.base.BaseViewHolder
import com.igluesmik.sopt.ui.itemtouch.ItemTouchHelperListener
import io.reactivex.plugins.RxJavaPlugins
import java.util.*

class ProfileAdapter() : RecyclerView.Adapter<BaseViewHolder>(), ItemTouchHelperListener {

    private val linearItemView = R.layout.item_profile
    private val gridItemView = R.layout.item_profile_grid
    private var itemViewType : Int = linearItemView
    private var onItemClickListener : ((Profile) -> Unit) ?= null

    private var _data = mutableListOf<Profile>()
    var data : List<Profile>
        get() = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }

    fun setOnItemClickListener(listener : (Profile) -> Unit) {
        this.onItemClickListener = listener
    }

    fun setGridItemViewType(){
        this.itemViewType = gridItemView
    }

    fun setLinearItemViewType(){
        this.itemViewType = linearItemView
    }

    inner class LinearViewHolder(private val binding: ItemProfileBinding) : BaseViewHolder(binding.root) {
        fun onBind(data : Profile) {
            binding.profile = data
            binding.layout.setOnClickListener {
                onItemClickListener?.invoke(data)
            }
        }
    }

    inner class GridViewHolder(private val binding: ItemProfileGridBinding) : BaseViewHolder(binding.root) {
        fun onBind(data : Profile) {
            binding.profile = data
            binding.layout.setOnClickListener {
                onItemClickListener?.invoke(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if(isLinearViewType()) {
            val binding = ItemProfileBinding.inflate(inflater, parent, false)
            LinearViewHolder(binding)
        } else {
            val binding = ItemProfileGridBinding.inflate(inflater, parent, false)
            GridViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if(isLinearViewType()){
            (holder as LinearViewHolder).onBind(_data[position])
        }
        else {
            (holder as GridViewHolder).onBind(_data[position])
        }
    }

    override fun getItemCount(): Int = _data.size

    override fun onItemMoved(from: Int, to: Int){
        Collections.swap(_data, from, to)
        notifyItemMoved(from, to)
    }

    override fun onItemSwiped(position: Int) {
        _data.removeAt(position)
        notifyItemRemoved(position)
    }

    private fun isLinearViewType(): Boolean{
        return this.itemViewType == linearItemView
    }
}