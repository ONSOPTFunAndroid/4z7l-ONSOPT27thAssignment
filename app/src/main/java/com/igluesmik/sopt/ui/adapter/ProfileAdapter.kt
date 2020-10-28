package com.igluesmik.sopt.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.igluesmik.sopt.R
import com.igluesmik.sopt.model.Profile
import com.igluesmik.sopt.ui.itemtouch.ItemTouchHelperListener
import java.util.*

class ProfileAdapter(private val context : Context) : RecyclerView.Adapter<ProfileAdapter.ViewHolder>(), ItemTouchHelperListener {

    private val data = mutableListOf<Profile>()

    private val linearItemView = R.layout.item_profile
    private val gridItemView = R.layout.item_profile_grid

    private var itemViewType : Int = linearItemView

    private var onItemClickListener : ((Profile) -> Unit) ?= null

    fun setData(data : List<Profile>) {
        this.data.clear()
        this.data.addAll(data)
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

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title : TextView = itemView.findViewById(R.id.title)
        private val subtitle : TextView = itemView.findViewById(R.id.subtitle)
        private val image : ImageView = itemView.findViewById(R.id.image)

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
        val view = LayoutInflater.from(context).inflate(itemViewType, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(data[position])
    }

    override fun getItemCount(): Int = data.size

    override fun onItemMoved(from: Int, to: Int){
        Collections.swap(data, from, to)
        notifyItemMoved(from, to)
    }

    override fun onItemSwiped(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }

}