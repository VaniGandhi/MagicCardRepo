package com.example.magiccards;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api_interface {



    @POST("get-images")
    Call<GetImagePojo> getimages(@Query("type") Integer type);

}
