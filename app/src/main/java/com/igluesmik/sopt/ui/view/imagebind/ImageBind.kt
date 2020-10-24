package com.igluesmik.sopt.ui.view.imagebind

import android.widget.ImageView
import androidx.databinding.BindingAdapter

object ImageBind {
    @JvmStatic
    @BindingAdapter("bindProfileImage")
    fun bindReviewImage(view: ImageView, resourceId: Int) {
        view.setImageResource(resourceId)
    }
}