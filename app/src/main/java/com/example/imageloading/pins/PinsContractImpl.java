package com.example.imageloading.pins;

import com.example.imageloading.utils.Constants;
import com.example.imageloading.webapi.RetrofitClient;
import com.example.imageloading.webapi.models.Pin;
import com.example.imageloading.webapi.models.PinsResponse;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PinsContractImpl implements PinsContract {
    @Override
    public void getPins(final String pinsCode, final Listener listener) {

        RetrofitClient.get().getPins(pinsCode).enqueue(new Callback<List<Pin>>() {
            @Override
            public void onResponse(Call<List<Pin>> call, Response<List<Pin>> response) {
                if (response.isSuccessful()) {
                    PinsResponse pinsResponse = new PinsResponse();
                    pinsResponse.setPins(response.body());
                    listener.onGetPinsSuccess(pinsResponse);
                } else {
                    listener.onGetPinsError(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Pin>> call, Throwable t) {
                if (t instanceof IOException) {
                    listener.onGetPinsError(Constants.INTERNET_NOT_FOUND);
                } else {
                    listener.onGetPinsError(t.getLocalizedMessage());
                }
            }
        });
    }
}
