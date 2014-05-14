package com.github.kevinmcmahon.ottoexample;

/**
 * Created by kevin on 5/14/14.
 */
public class Location {
    public final float latitude;
    public final float longitude;

    public Location(float latitude, float longitude) {
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
