package com.casper.thetmb.thetmb;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by casper on 09.05.16.
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_ac);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getFragmentManager().beginTransaction().replace(R.id.fr_place_holder, new MainFragment()).commit();
    }
}