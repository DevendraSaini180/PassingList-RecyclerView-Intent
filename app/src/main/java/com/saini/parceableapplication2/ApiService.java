package com.saini.parceableapplication2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("v2/list")
    Call<List<PhotoModel>> getPhotos();


//    @GET("posts/1")
//    Call<PostModel> getPost();
}
