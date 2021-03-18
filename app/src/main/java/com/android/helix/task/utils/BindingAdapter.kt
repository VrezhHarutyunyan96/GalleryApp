package com.android.helix.task.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("image")
fun loadImage(view: ImageView, url: String?) {
    url?.let {
        view.load("https" + url.substring(4, url.length))
    }
}

@BindingAdapter("visibility")
fun setVisibility(view: View, state: Boolean) {
    view.visibility = if (state) View.VISIBLE else View.GONE
}