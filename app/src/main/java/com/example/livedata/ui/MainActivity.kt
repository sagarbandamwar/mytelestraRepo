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

        mainViewModel = ViewModelProviders.of(this@MainActivity).get(MainViewModel::class.java)  // creating reference of view model class

        sendGet()  // calls the api and fetch the data from the server


        // this method will set the color to swi[pe refresh progressbar
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(
            ContextCompat.getColor(
                this,
                R.color.colorPrimary
            )
        )
        swipeRefreshLayout.setColorSchemeColors(Color.WHITE)

       // this layout will get the fresh data whenever user swipes the screen
        swipeRefreshLayout.setOnRefreshListener {
            if (CommonUtil.isOnline(this@MainActivity)) {
                sendGet()
                swipeRefreshLayout.isRefreshing = false  // once the data is fetched loading view will stop
            } else {
                CommonUtil.showDialog(getString(R.string.internet_message) ,this) // it will show popup if not connected to internet
                swipeRefreshLayout.isRefreshing = false
            }
        }

    }

    // it will fetch the updated data from view model
    private fun sendGet() {
        swipeRefreshLayout.setRefreshing(true)
        if (CommonUtil.isOnline(MainApplication.applicationContext())) {
            mainViewModel.allBlog
                .observe(this,
                    Observer { blogList -> // if there is any new data over api observer will update automatically
                        swipeRefreshLayout.setRefreshing(false)
                        prepareRecyclerView(blogList)   // this method will setup recycler view to activity with help of layout manager
                    })
        }else{
            CommonUtil.showDialog(getString(R.string.internet_message) ,this)  // it will show popup to connect to internet
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
                var position : Int = linearLayoutManager.findFirstCompletelyVisibleItemPosition()  // capturing the position of view to update the title bar
                if (position>0){
                    if (blogList.get(position).title.equals("null"))
                    { supportActionBar?.title= "No Title"                // if there is no title we are setting No title as a text instead showing null
                    }else{
                        supportActionBar?.title= blogList.get(position).title    // updating the title according to api response
                    }
                }
            }
        })
    }
}
