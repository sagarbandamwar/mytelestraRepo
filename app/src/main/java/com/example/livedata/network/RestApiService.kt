package com.example.livedata.network

import com.example.livedata.data.BlogWrapper
import retrofit2.Call
import retrofit2.http.GET

// it will call our api with the help of api endpoints
interface RestApiService {
    @get:GET("facts")
    val popularBlog: Call<BlogWrapper>?
}