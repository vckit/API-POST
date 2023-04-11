package com.example.myapplication;

import android.util.Log;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String API_URL = "https://medic.madskill.ru/api/sendCode";

    public static void sendEmail(String email) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://medic.madskill.ru/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EmailApi emailApi = retrofit.create(EmailApi.class);
        Call<ResponseBody> call = emailApi.sendGeneratedPassword(email);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d("ApiClient", "Email sent successfully");
                } else {
                    Log.e("ApiClient", "Error sending email: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("ApiClient", "Error sending email: " + t.getMessage(), t);
            }
        });
    }
}
