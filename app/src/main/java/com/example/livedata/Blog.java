package com.example.livedata;

import com.google.gson.annotations.SerializedName;

public class Blog {
    @SerializedName("title")
    private String mTitle;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("imageHref")
    private String mImageHref;

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmImageHref() {
        return mImageHref;
    }

    public void setmImageHref(String mImageHref) {
        this.mImageHref = mImageHref;
    }
}
