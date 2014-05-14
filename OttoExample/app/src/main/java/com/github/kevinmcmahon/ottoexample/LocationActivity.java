package com.github.kevinmcmahon.ottoexample;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.squareup.otto.Produce;

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

                BusProvider.getInstance().post(new LocationClearEvent());
                lastLatitude = DEFAULT_LAT;
                lastLongitude = DEFAULT_LON;

                BusProvider.getInstance().post(produceLocationEvent());
            }
        });

        findViewById(R.id.move_location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastLatitude += (RANDOM.nextFloat() * OFFSET * 2) - OFFSET;
                lastLongitude += (RANDOM.nextFloat() * OFFSET * 2) - OFFSET;
                BusProvider.getInstance().post(produceLocationEvent());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Register ourselves so that we can provide the initial value.
        BusProvider.getInstance().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Always unregister when an object no longer should be on the bus.
        BusProvider.getInstance().unregister(this);
    }

    @Produce
    public LocationChangedEvent produceLocationEvent() {
        // Provide an initial value for location based on the last known position.
        return new LocationChangedEvent(lastLatitude, lastLongitude);
    }
}
