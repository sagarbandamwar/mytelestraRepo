package com.example.livedata.ui

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.livedata.R
import com.example.livedata.adapter.AdapterRow
import com.example.livedata.data.Blog
import com.example.livedata.util.AppConstants
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

   lateinit var  mainViewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        sendGet()

        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(
            ContextCompat.getColor(
                this,
                R.color.colorPrimary
            )
        )
        swipeRefreshLayout.setColorSchemeColors(Color.WHITE)

        swipeRefreshLayout.setOnRefreshListener {
            if (AppConstants.isOnline(this)) {
                sendGet()
                swipeRefreshLayout.isRefreshing = false
            } else {
                AppConstants.showDialog("Connect to Internet" ,this)
                swipeRefreshLayout.isRefreshing = false
            }
        }

    }

    private fun sendGet() {
        swipeRefreshLayout.setRefreshing(true)
        mainViewModel.allBlog
            .observe(this,
                Observer { blogList ->
                    swipeRefreshLayout.setRefreshing(false)
                    prepareRecyclerView(blogList)
                })
    }

    private fun prepareRecyclerView(blogList: List<Blog>) {
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        recyclerview.layoutManager = linearLayoutManager
        val mBlogAdapter = AdapterRow(
            this@MainActivity,
            blogList as ArrayList<Blog>
        )
        recyclerview.adapter = mBlogAdapter
        mBlogAdapter.notifyDataSetChanged()
    }
}
