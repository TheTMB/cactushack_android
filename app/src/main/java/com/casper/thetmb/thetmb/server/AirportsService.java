package com.casper.thetmb.thetmb.server;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by casper on 11.05.16.
 */
public interface AirportsService {

    @GET("/places/coords_to_places_ru.json")
    Call<List<Airport>> airports(@Query("coords") String gps);

}
