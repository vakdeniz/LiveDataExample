package com.vakdeniz.livedataexample.utils

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.vakdeniz.livedataexample.view.BlogAdapter

@BindingAdapter("bind:imageUrl")
fun imageUrl(view: ImageView, imageURL: String?){
    if (imageURL != null) {
        Glide.with(BlogAdapter.getContext())
            .load(imageURL)
            .into(view)
    }
}