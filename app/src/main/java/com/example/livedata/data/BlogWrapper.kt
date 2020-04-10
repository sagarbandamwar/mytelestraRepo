package com.example.livedata.data

import com.google.gson.annotations.SerializedName

class BlogWrapper {
    @SerializedName("rows")
    private var mRows: List<Blog>? = null

    @SerializedName("title")
    private var mTitle: String? = null
    fun getmRows(): List<Blog>? {
        return mRows
    }

    fun setmRows(mRows: List<Blog>?) {
        this.mRows = mRows
    }

    fun getmTitle(): String? {
        return mTitle
    }

    fun setmTitle(mTitle: String?) {
        this.mTitle = mTitle
    }
}