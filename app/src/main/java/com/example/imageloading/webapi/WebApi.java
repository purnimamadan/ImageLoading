package com.example.imageloading.webapi;

import com.example.imageloading.webapi.models.Pin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WebApi {

    String API = " http://pastebin.com/";
    String apiVersion = "";

    @GET(apiVersion + "raw/{pins_code}")
    Call<List<Pin>> getPins(@Path("pins_code") String pinsCode);

}
