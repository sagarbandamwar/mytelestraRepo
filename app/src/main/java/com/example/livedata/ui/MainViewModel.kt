package com.example.livedata.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.livedata.data.Blog
import com.example.livedata.data.BlogRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val blogRepository: BlogRepository
    val allBlog: LiveData<List<Blog>>
        get() = blogRepository.getMutableLiveData()  // get method will store all data from blog repository to all blog reference

    init {
        blogRepository = BlogRepository(application)
    }
}