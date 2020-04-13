package com.example.livedata.data

import com.google.gson.annotations.SerializedName

// data class to get array elements
data class Blog(
    @SerializedName("title")
     var title: String? = null,

    @SerializedName("description")
     var description: String? = null,

    @SerializedName("imageHref")
     var imageHref: String? = null)