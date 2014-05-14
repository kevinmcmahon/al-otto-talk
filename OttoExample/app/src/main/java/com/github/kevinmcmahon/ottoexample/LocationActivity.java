package com.github.kevinmcmahon.ottoexample;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import java.util.Random;

public class LocationActivity extends FragmentActivity {
    public static final float DEFAULT_LAT = 41.878876f;
    public static final float DEFAULT_LON = -87.635915f;
    private static final float OFFSET = 0.1f;
    private static final Random RANDOM = new Random();

    private static float lastLatitude = DEFAULT_LAT;
    private static float lastLongitude = DEFAULT_LON;

    private LocationHistoryFragment historyFragment;
    private LocationMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_history);

        historyFragment = (LocationHistoryFragment) getFragmentManager().findFragmentById(R.id.history);
        mapFragment = (LocationMapFragment) getFragmentManager().findFragmentById(R.id.map);

        findViewById(R.id.clear_location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Rest to default location
                lastLatitude = DEFAULT_LAT;
                lastLongitude = DEFAULT_LON;
                Location location = new Location(lastLatitude, lastLongitude);
                mapFragment.setLocation(location);
                historyFragment.clearLocations();
                historyFragment.addLocation(location);
            }
        });

        findViewById(R.id.move_location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastLatitude += (RANDOM.nextFloat() * OFFSET * 2) - OFFSET;
                lastLongitude += (RANDOM.nextFloat() * OFFSET * 2) - OFFSET;
                Location location = new Location(lastLatitude, lastLongitude);
                mapFragment.setLocation(location);
                historyFragment.addLocation(location);
            }
        });

        // set initial location
        Location location = new Location(DEFAULT_LAT, DEFAULT_LON);
        mapFragment.setLocation(location);
        historyFragment.addLocation(location);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
