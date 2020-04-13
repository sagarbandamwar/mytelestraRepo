package com.example.livedata.data

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.livedata.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class BlogRepository(private val application: Application) {
    private var blogs = ArrayList<Blog>()  // arraylist of data class blog
    private val mutableLiveData = MutableLiveData<List<Blog>>() // creating referece of mutable live data

    fun getMutableLiveData(): MutableLiveData<List<Blog>> {
        val apiService = RetrofitInstance.apiService  // WE ARE CALLING API HERE WITH RETROFIT 2 LIBRARY
        val call = apiService.popularBlog           // HERE WE RECEIVE THE WHOLE LIST OF BLOGS
        call?.enqueue(object : Callback<BlogWrapper?> {
            override fun onResponse(
                call: Call<BlogWrapper?>,
                response: Response<BlogWrapper?>
            ) {
                val mBlogWrapper = response.body()
                if (mBlogWrapper != null && mBlogWrapper.rows!= null) {
                    blogs = mBlogWrapper.rows as ArrayList<Blog>
                    mutableLiveData.value = blogs     // storing received list to mutable live data
                }
            }

            override fun onFailure(                     // if the network call gets fauled it will call on failure
                call: Call<BlogWrapper?>,
                t: Throwable
            ) {
                t.printStackTrace()                       // printStackTrace it will print the error cause
            }
        })
        return mutableLiveData                           // finally this call will returen mutableLiveData
    }

}