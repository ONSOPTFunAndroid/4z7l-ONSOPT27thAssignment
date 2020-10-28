package com.igluesmik.sopt.ui.bind

import android.webkit.WebView
import android.widget.ImageView
import androidx.databinding.BindingAdapter

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("bindProfileImage")
    fun bindProfileImage(view: ImageView, resourceId: Int) {
        view.setImageResource(resourceId)
    }

    @JvmStatic
    @BindingAdapter("bindWebViewUrl")
    fun bindWebViewUrl(view: WebView, url: String?) {
        if(!url.isNullOrEmpty())
            view.loadUrl(url)
    }
}