package com.example.livedata.data

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.livedata.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class BlogRepository(private val application: Application) {
    private var movies = ArrayList<Blog>()
    private val mutableLiveData =
        MutableLiveData<List<Blog>>()

    fun getMutableLiveData(): MutableLiveData<List<Blog>> {
        val apiService = RetrofitInstance.apiService
        val call = apiService.popularBlog
        call.enqueue(object : Callback<BlogWrapper?> {
            override fun onResponse(
                call: Call<BlogWrapper?>,
                response: Response<BlogWrapper?>
            ) {
                val mBlogWrapper = response.body()
                if (mBlogWrapper != null && mBlogWrapper.getmRows() != null) {
                    movies = mBlogWrapper.getmRows() as ArrayList<Blog>
                    mutableLiveData.value = movies
                }
            }

            override fun onFailure(
                call: Call<BlogWrapper?>,
                t: Throwable
            ) {
            }
        })
        return mutableLiveData
    }

}