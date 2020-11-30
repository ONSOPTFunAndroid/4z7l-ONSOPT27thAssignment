package com.igluesmik.sopt.ui.bind

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("bindUrlImage")
fun bindUrlImage(view: ImageView, url: String) {
    Glide.with(view.context)
        .load(url)
        .into(view)
}

@BindingAdapter("bindImageResource")
fun bindImageResource(view: ImageView, id: Int) {
    view.setImageResource(id)
}