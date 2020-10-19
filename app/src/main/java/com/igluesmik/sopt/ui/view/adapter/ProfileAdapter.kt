package com.igluesmik.sopt.ui.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.igluesmik.sopt.R
import com.igluesmik.sopt.model.Profile
import kotlinx.android.synthetic.main.item_profile.view.*

class ProfileAdapter(private val context : Context) : RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

    var data = mutableListOf<Profile>()
        set(value) {
            field = value
        }

    var onItemClickListener : ((Profile) -> Unit) ?= null
        set(value) {
            field = value
        }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title : TextView = itemView.title
        private val subtitle : TextView = itemView.subtitle
        private val image : ImageView = itemView.image

        fun onBind(data : Profile) {
            title.text = data.title
            subtitle.text = data.subtitle
            image.setImageResource(data.resourceId)

            itemView.setOnClickListener {
                onItemClickListener?.invoke(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_profile, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(data[position])
    }

    override fun getItemCount(): Int = data.size

}