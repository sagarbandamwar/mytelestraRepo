package com.example.livedata.data

import com.google.gson.annotations.SerializedName

// writing data class for blogs to get arraylist
data class BlogWrapper(
    @SerializedName("rows")
     var rows: List<Blog>? = null,
    @SerializedName("title")
     var title: String? = null)