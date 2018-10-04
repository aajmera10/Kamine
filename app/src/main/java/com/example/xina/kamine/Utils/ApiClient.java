package com.example.xina.kamine.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    //publichttp://xinatechnologies.com/JDL/api/  http://www.jaindarshanlive.com/api/    static final String BASE_URL = "https://xinatechnologies.com/jdl/";
    public static final String BASE_URL = "http://www.xinatechnologies.com/kamine/";
    //http://xinatechnologies.com/JDL/api/channel.php
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            Gson gson = new GsonBuilder()
                    .setLenient().create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}

