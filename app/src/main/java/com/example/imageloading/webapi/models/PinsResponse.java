package com.example.imageloading.webapi.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class PinsResponse {

    @Expose
    List<Pin> pins = new ArrayList<>();

    public PinsResponse() {
    }

    public List<Pin> getPins() {
        return pins;
    }

    public void setPins(List<Pin> pins) {
        this.pins = pins;
    }
}
