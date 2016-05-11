package com.casper.thetmb.thetmb;

import android.app.Fragment;

import com.casper.thetmb.thetmb.server.Airport;
import com.casper.thetmb.thetmb.server.AirportsService;
import com.casper.thetmb.thetmb.server.ApiFactory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by casper on 11.05.16.
 */
public class MainFragment extends Fragment implements Callback<List<Airport>> {

    @Override
    public void onResume() {
        super.onResume();
        AirportsService service = ApiFactory.getAirportsService();
        Call<List<Airport>> call = service.airports("55.749792,37.6324949");
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Airport>> call, Response<List<Airport>> response) {
        if (response.isSuccessful()) {
            List<Airport> airports = response.body();
        }
    }

    @Override
    public void onFailure(Call<List<Airport>> call, Throwable t) {

    }
}
