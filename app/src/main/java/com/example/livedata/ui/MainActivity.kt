package com.example.livedata.ui

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.livedata.MainApplication
import com.example.livedata.R
import com.example.livedata.adapter.AdapterRow
import com.example.livedata.data.Blog
import com.example.livedata.util.CommonUtil
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

   lateinit var  mainViewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this@MainActivity).get(MainViewModel::class.java)

        sendGet()

        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(
            ContextCompat.getColor(
                this,
                R.color.colorPrimary
            )
        )
        swipeRefreshLayout.setColorSchemeColors(Color.WHITE)

        swipeRefreshLayout.setOnRefreshListener {
            if (CommonUtil.isOnline(this@MainActivity)) {
                sendGet()
                swipeRefreshLayout.isRefreshing = false
            } else {
                // it will show popup if not connected to internet
                CommonUtil.showDialog(getString(R.string.internet_message) ,this)
                swipeRefreshLayout.isRefreshing = false
            }
        }

    }

    private fun sendGet() {
        // it eill fetch the updated data from view model
        swipeRefreshLayout.setRefreshing(true)
        if (CommonUtil.isOnline(MainApplication.applicationContext())) {
            mainViewModel.allBlog
                .observe(this,
                    Observer { blogList ->
                        swipeRefreshLayout.setRefreshing(false)
                        prepareRecyclerView(blogList)
                    })
        }else{
            CommonUtil.showDialog(getString(R.string.internet_message) ,this)
            swipeRefreshLayout.isRefreshing = false
        }
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

        // this scroll listener will update the actionbar title

        recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                var position : Int = linearLayoutManager.findFirstCompletelyVisibleItemPosition()
                if (position>0){

                    if (blogList.get(position).mTitle.equals("null"))
                    { supportActionBar?.title= "No Title"
                    }else{
                        supportActionBar?.title= blogList.get(position).mTitle
                    }
                }

            }
        })
    }
}
