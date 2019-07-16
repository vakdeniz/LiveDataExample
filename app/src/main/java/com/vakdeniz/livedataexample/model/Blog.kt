package com.vakdeniz.livedataexample.model

import com.google.gson.annotations.SerializedName
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.Glide
import android.widget.ImageView


data class Blog(
    @SerializedName("author")
    var author: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("link")
    var link: String? = null,
    @SerializedName("pubDate")
    var pubDate: String? = null,
    @SerializedName("thumbnail")
    var thumbnail: String? = null,
    @SerializedName("title")
    var title: String? = null
) {
    companion object {
        fun loadImage(view: ImageView, imageURL: String) {
            Glide.with(view.context)
                .load(imageURL)
                .apply(RequestOptions.circleCropTransform())
                .into(view)
        }
    }



    // If you consider Picasso, follow the below
    // Picasso.with(view.getContext()).load(imageUrl).placeholder(R.drawable.placeholder).into(view);
}