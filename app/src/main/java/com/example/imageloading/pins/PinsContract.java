package com.example.imageloading.pins;

import com.example.imageloading.base.BaseView;
import com.example.imageloading.webapi.models.PinsResponse;

public interface PinsContract {

    void getPins(String pinsCode, Listener listener);

    interface View extends BaseView {
        void onGetPinsSuccess(PinsResponse pinsResponse);

        void onGetPinsError(String message);

        void onPinClicked(int position);
    }

    interface Presenter {
        void getPins(String pinsCode);
    }

    interface Listener {
        void onGetPinsSuccess(PinsResponse pinsResponse);

        void onGetPinsError(String message);
    }
}
