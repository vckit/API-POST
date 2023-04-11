package com.example.myapplication;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface EmailApi {
    @POST("api/sendCode")
    @Headers("Content-Type: application/json")
    Call<ResponseBody> sendGeneratedPassword(@retrofit2.http.Header("email") String email);
}

