package com.mim.tutorprojecttest.api;

import com.mim.tutorprojecttest.classes.Details;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public interface APIInterface {



    @GET("ce1943d6-41e4-4d8d-8509-e9cb6033f53f")
    Call<Details> GetList();


}