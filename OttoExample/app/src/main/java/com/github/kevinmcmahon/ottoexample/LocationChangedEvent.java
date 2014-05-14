package com.github.kevinmcmahon.ottoexample;

public class LocationChangedEvent {

    public final float latitude;
    public final float longitude;

    public LocationChangedEvent(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return new StringBuilder("(") //
                .append(latitude) //
                .append(", ") //
                .append(longitude) //
                .append(")") //
                .toString();
    }
}
