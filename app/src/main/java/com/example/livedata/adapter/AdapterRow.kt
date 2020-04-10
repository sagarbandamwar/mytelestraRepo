package com.example.livedata.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.livedata.data.Blog
import com.example.livedata.R
import com.example.livedata.communication.RecyclerViewScrollListener
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class AdapterRow constructor(mContext: Context, mArrayListString: ArrayList<Blog>) :
    RecyclerView.Adapter<AdapterRow.Holder>() {

    var mContext: Context = mContext
    var mArrayListString: ArrayList<Blog> = mArrayListString
    var recyclerViewScrollListener: RecyclerViewScrollListener? = null
    var recyclerScroll: RecyclerView.OnScrollListener? = null

    fun AdapterRow(
        mContext: Context,
        mArrayListString: ArrayList<Blog>,
        recyclerViewScrollListener: RecyclerViewScrollListener
    ) {
        this.mContext = mContext
        this.mArrayListString = mArrayListString
        this.recyclerViewScrollListener = recyclerViewScrollListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.blog_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return mArrayListString.size  // it wiil return size of a arraylist
    }


    override fun onBindViewHolder(holder: Holder, position: Int) {
        val titles = mArrayListString.get(position)

        var mTitle: String? = titles.getmTitle()
        holder.mTitle.text = mTitle
        holder.mSubTitle.text = titles.getmDescription()
        val mImageUrl: String? = titles.getmImageHref()

        //picasso.setIndicatorsEnabled(true)
        mImageUrl.let {

            Picasso.get().load(mImageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground).into(holder.mImage, object : Callback {
                    override fun onSuccess() {

                    }

                    override fun onError(e: Exception) {
                        e.printStackTrace()
                    }

                })
        }

    }


    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTitle: TextView
        val mSubTitle: TextView
        val mImage: ImageView

        init {
            mTitle = itemView.findViewById(R.id.title)
            mSubTitle = itemView.findViewById(R.id.subtitle)
            mImage = itemView.findViewById(R.id.image)
        }
    }

}


