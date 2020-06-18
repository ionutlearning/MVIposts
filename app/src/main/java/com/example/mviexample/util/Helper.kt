package com.example.mviexample.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    val currentDrawable = view.drawable
    val options = RequestOptions()
        .placeholder(getProgressDrawable(view.context))
        .error(currentDrawable)
    Glide.with(view.context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(view)
}

private fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}