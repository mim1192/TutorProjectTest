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



    @GET("0dfe31fb-af2a-432d-9af5-c4e6fb21ef27")
    Call<Details> GetList();


}