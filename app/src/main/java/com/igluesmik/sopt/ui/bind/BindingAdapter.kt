package com.igluesmik.sopt.ui.bind

import android.widget.ImageView
import androidx.databinding.BindingAdapter

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("bindProfileImage")
    fun bindProfileImage(view: ImageView, resourceId: Int) {
        view.setImageResource(resourceId)
    }
}