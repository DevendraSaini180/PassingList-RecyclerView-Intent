package com.saini.parceableapplication2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    String mUrl = "https://picsum.photos/";
//    String mUrl = "https://jsonplaceholder.typicode.com/";
    RecyclerView mRecyclerView;
    Retrofit mRetrofit;
    List<PhotoModel> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewInitialization();
        getRetrofit();
        getApiInstance();
    }

    public void viewInitialization() {
        mRecyclerView = findViewById(R.id.recyclerVView);
    }

    public void getRetrofit() {
        mRetrofit = new Retrofit.Builder().baseUrl(mUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void getApiInstance() {
        ApiService apiService = mRetrofit.create(ApiService.class);
//        Call<PostModel> modelCall = apiService.getPost();
//        modelCall.enqueue(new Callback<PostModel>() {
//            @Override
//            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
//                PostModel list = response.body();
//                Intent intent = new Intent(MainActivity.this, ReceivingActivity.class);
//                intent.putExtra("post", (Parcelable) list);
//                startActivity(intent);
//            }
//
//            @Override
//            public void onFailure(Call<PostModel> call, Throwable t) {
//
//            }
//        });

        Call<List<PhotoModel>> apiCall = apiService.getPhotos();
        apiCall.enqueue(new Callback<List<PhotoModel>>() {
            @Override
            public void onResponse(Call<List<PhotoModel>> call, Response<List<PhotoModel>> response) {
                mList = response.body();

                CustomAdapter adapter = new CustomAdapter(getApplication(), mList);
                mRecyclerView.setAdapter(adapter);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL, false);
                mRecyclerView.setLayoutManager(layoutManager);

//                Intent intent = new Intent(MainActivity.this, ReceivingActivity.class);
//                intent.putExtra("ApiList", mList);
            }

            @Override
            public void onFailure(Call<List<PhotoModel>> call, Throwable t) {

            }
        });

    }
}