package com.github.kevinmcmahon.ottoexample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Random;

public class LocationActivity extends FragmentActivity {
    public static final float DEFAULT_LAT = 40.440866f;
    public static final float DEFAULT_LON = -79.994085f;
    private static final float OFFSET = 0.1f;
    private static final Random RANDOM = new Random();

    private static float lastLatitude = DEFAULT_LAT;
    private static float lastLongitude = DEFAULT_LON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_history);

        findViewById(R.id.clear_location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Rest to default location
                lastLatitude = DEFAULT_LAT;
                lastLongitude = DEFAULT_LON;
            }
        });

        findViewById(R.id.move_location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastLatitude += (RANDOM.nextFloat() * OFFSET * 2) - OFFSET;
                lastLongitude += (RANDOM.nextFloat() * OFFSET * 2) - OFFSET;
            }
        });
    }

    @Override protected void onResume() {
        super.onResume();
    }

    @Override protected void onPause() {
        super.onPause();
    }
}
