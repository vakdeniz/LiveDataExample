package com.vakdeniz.livedataexample.model

import com.google.gson.annotations.SerializedName

data class BlogWrapper(
    @SerializedName("data")
    var blog: MutableList<Blog>? = null,
    @SerializedName("error")
    var error: Boolean? = null,
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("status")
    var status: String? = null
)