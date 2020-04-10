package com.example.livedata.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    var BASE_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/"
    private var retrofit: Retrofit? = null
    val apiService: RestApiService
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!.create(RestApiService::class.java)
        }
}