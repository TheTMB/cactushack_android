package com.casper.thetmb.thetmb.server;

import com.google.gson.annotations.SerializedName;

/**
 * Created by casper on 11.05.16.
 */
public class Airport {
    @SerializedName("iata")
    private String mIata;

    @SerializedName("name")
    private String mName;

    @SerializedName("airport_name")
    private String mAirportName;

    public Airport() {
    }
}