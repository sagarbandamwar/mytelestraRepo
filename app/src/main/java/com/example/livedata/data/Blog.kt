package com.example.livedata.data

import com.google.gson.annotations.SerializedName

class Blog {
    @SerializedName("title")
     var mTitle: String? = null

    @SerializedName("description")
     var mDescription: String? = null

    @SerializedName("imageHref")
     var mImageHref: String? = null
    fun getmTitle(): String? {
        return mTitle
    }

    fun setmTitle(mTitle: String?) {
        this.mTitle = mTitle
    }

    fun getmDescription(): String? {
        return mDescription
    }

    fun setmDescription(mDescription: String?) {
        this.mDescription = mDescription
    }

    fun getmImageHref(): String? {
        return mImageHref
    }

    fun setmImageHref(mImageHref: String?) {
        this.mImageHref = mImageHref
    }
}