package com.example.livedata.network

import android.app.usage.UsageEvents.Event.NONE
import android.util.Log
import com.example.livedata.BuildConfig

import com.example.livedata.MainApplication
import com.example.livedata.util.CommonUtil
import okhttp3.*
import okhttp3.CacheControl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit
import java.util.logging.Logger


object RetrofitInstance {
    var BASE_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/"
    private const val CACHE_CONTROL = "Cache-Control"
    val cacheSize = (10 * 1024 * 1024).toLong() // we are declaring our cache size as 10MB
//    val myCache = Cache(MainApplication.applicationContext()!!.cacheDir, cacheSize)

    val okHttpClient = OkHttpClient.Builder()   // creating reference forokhttp client
        .addNetworkInterceptor( provideCacheInterceptor())
        .cache(provideCache())
        .build()
    private var retrofit: Retrofit? = null   // reference for retrofit
    val apiService: RestApiService
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())  // gson converter for json data
                    .client(okHttpClient) // by adding okhhtpClient we are enabling caching in applikcation
                    .build()
            }
            return retrofit!!.create(RestApiService::class.java)
        }


    fun provideOfflineCacheInterceptor(): Interceptor? {  // class for cache offline interceptor
        return object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response? {
                var request: Request = chain.request()
                if (!CommonUtil.isOnline(MainApplication?.applicationContext())) {
                    val cacheControl = CacheControl.Builder()  // it will give time for how may days cache will store data
                        .maxStale(7, TimeUnit.DAYS)
                        .build()
                    request = request.newBuilder()
                        .cacheControl(cacheControl)
                        .build()
                }
                return chain.proceed(request)
            }
        }
    }

    fun provideCacheInterceptor(): Interceptor? {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())

            // re-write response header to force use of cache
            val cacheControl = CacheControl.Builder()
                .maxAge(2, TimeUnit.MINUTES)
                .build()
            response.newBuilder()
                .header(CACHE_CONTROL,cacheControl.toString())
                .build()
        }
    }
    private fun provideCache(): Cache? {   // this will retuen cache size
        var cache: Cache? = null
        try {
            cache = Cache(
                File(
                    MainApplication.applicationContext().getCacheDir(), "http-cache"
                ),
                10 * 1024 * 1024   // it will return 10 mb
            ) // 10 MB
        } catch (e: Exception) {
            Log.e("", "Could not create Cache!"+e.printStackTrace().toString())  // if cache is not returned
        }
        return cache
    }
}


