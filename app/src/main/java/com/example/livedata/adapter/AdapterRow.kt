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

    var mContext: Context = mContext               // reference for context
    var mArrayListString: ArrayList<Blog> = mArrayListString   // reference for aaraylist blogs

    
    // it will creater view holder and retuen the vies defined in the laypot
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.blog_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return mArrayListString.size  // it wiil return size of a arraylist
    }


    override fun onBindViewHolder(holder: Holder, position: Int) {
        val titles = mArrayListString.get(position)

        var mTitle: String? = titles.title      // we are retriving title from the array list which received from view model
        holder.mTitle.text = mTitle
        holder.mSubTitle.text = titles.description      // we are retriving description from the array list which received from view model
        val mImageUrl: String? = titles.imageHref       // we are retriving image LINK from the array list which received from view model

        //picasso.setIndicatorsEnabled(true)
        mImageUrl.let {
            Picasso.get().load(mImageUrl)                          // we are loading images with the help of picasso
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground).into(holder.mImage, object : Callback {
                    override fun onSuccess() {

                    }

                    override fun onError(e: Exception) {
                        e.printStackTrace()         // it will print the exception
                    }

                })
        }

    }


    // holder class to bind the views with the recyclerview
    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTitle: TextView
        val mSubTitle: TextView
        val mImage: ImageView

        init {
            mTitle = itemView.findViewById(R.id.title)  // finding view for title
            mSubTitle = itemView.findViewById(R.id.subtitle)  // finding view for subtitle
            mImage = itemView.findViewById(R.id.image)   //  finding view for image
        }
    }

}


