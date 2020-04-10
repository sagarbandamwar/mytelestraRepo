package com.example.livedata;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApiService {
    @GET("facts")
    Call<BlogWrapper> getPopularBlog();
}
