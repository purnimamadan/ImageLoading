package com.example.imageloading.pins;

import com.example.imageloading.webapi.models.PinsResponse;

public class PinsPresenterImpl implements PinsContract.Presenter, PinsContract.Listener {

    PinsContract.View view;
    PinsContract contract;

    public PinsPresenterImpl(PinsContract.View view) {
        this.view = view;
        this.contract = new PinsContractImpl();
    }

    @Override
    public void getPins(String pinsCode) {
        view.showProgress();
        contract.getPins(pinsCode, this);
    }

    @Override
    public void onGetPinsSuccess(PinsResponse pinsResponse) {
        view.hideProgress();
        view.onGetPinsSuccess(pinsResponse);
    }

    @Override
    public void onGetPinsError(String message) {
        view.hideProgress();
        view.onGetPinsError(message);
    }
}
