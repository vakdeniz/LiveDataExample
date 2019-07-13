package com.vakdeniz.livedataexample.model

import com.google.gson.annotations.SerializedName

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
)