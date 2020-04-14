package com.example.livedata.ui

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.livedata.MainApplication
import com.example.livedata.R
import com.example.livedata.adapter.AdapterRow
import com.example.livedata.data.Blog
import com.example.livedata.util.CommonUtil
import kotlinx.android.synthetic.main.fragment_blog.*

class BlogsFragment:Fragment(){

    lateinit var  mainViewModel : MainViewModel
    companion object {
        fun newInstance(): BlogsFragment {
            return BlogsFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_blog, container, false)
        Log.d(BlogsFragment::class.simpleName ,"Inside BlogsFragment onCreateView")

    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }


    // it will fetch the updated data from view model
    private fun sendGet( ) {
        swipeRefreshLayout.setRefreshing(true)
        if (CommonUtil.isOnline(context)) {
            mainViewModel.allBlog
                .observe(viewLifecycleOwner,
                    Observer { blogList -> // if there is any new data over api observer will update automatically
                        swipeRefreshLayout.setRefreshing(false)
                        prepareRecyclerView(blogList)   // this method will setup recycler view to activity with help of layout manager
                    })
        }else{
            CommonUtil.showDialog(getString(R.string.internet_message),activity as Context)  // it will show popup to connect to internet
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun prepareRecyclerView(blogList: List<Blog>) {
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerview.layoutManager = linearLayoutManager
        val mBlogAdapter = AdapterRow(
            activity as Context,
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
                    {
                        (activity as AppCompatActivity).title= getString(R.string.mo_title)                // if there is no title we are setting No title as a text instead showing null
                    }else{
                        (activity as AppCompatActivity).title= blogList.get(position).title    // updating the title according to api response
                    }
                }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(BlogsFragment::class.simpleName ,"Inside BlogsFragment onViewCreated")
        val activity = activity as Context
        if (activity != null) {
            // mainViewModel = ViewModelProviders.of().get(MainViewModel::class.java)  // creating reference of view model class
            mainViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(MainApplication())
                .create(MainViewModel::class.java)

            sendGet()  // calls the api and fetch the data from the server

            // this method will set the color to swi[pe refresh progressbar
            swipeRefreshLayout.setProgressBackgroundColorSchemeColor(
                ContextCompat.getColor(
                    activity,
                    R.color.colorPrimary
                )
            )
            swipeRefreshLayout.setColorSchemeColors(Color.WHITE)

            // this layout will get the fresh data whenever user swipes the screen
            swipeRefreshLayout.setOnRefreshListener {
                if (CommonUtil.isOnline(activity)) {
                    sendGet()
                    swipeRefreshLayout.isRefreshing =
                        false  // once the data is fetched loading view will stop
                } else {
                    CommonUtil.showDialog(
                        getString(R.string.internet_message),
                        activity
                    ) // it will show popup if not connected to internet
                    swipeRefreshLayout.isRefreshing = false
                }
            }

        }
    }
}