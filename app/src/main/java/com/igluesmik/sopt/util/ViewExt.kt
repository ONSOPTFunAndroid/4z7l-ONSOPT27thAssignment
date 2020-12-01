package com.igluesmik.sopt.util

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

fun Context.shortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.setupToast(
    lifecycleOwner: LifecycleOwner,
    toastEvent: LiveData<Event<String>>
) {
    toastEvent.observe(lifecycleOwner, { event ->
        event.getContentIfNotHandled()?.let {
            shortToast(it)
        }
    })
}