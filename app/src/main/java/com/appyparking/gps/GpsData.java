package com.appyparking.gps;

import android.content.Context;
import com.google.android.gms.location.LocationClient;
import android.location.*;
import android.os.Bundle;
import android.util.Log;

import java.lang.String;

/**
 * Created by kartik on 02/12/14.
 */
public class GpsData {

    protected LocationManager locationManager;
    Location location;
    double latitude, longitude;
    final long MIN_TIME_BW_UPDATES = 1200;
    final float MIN_DISTANCE_CHANGE_FOR_UPDATES = 1000;
    Context mContext;
    boolean canGetLocation;

    public GpsData(Context context) {
        this.mContext = context;
        getLocation();
    }

    public Location getLocation() {
        try {
            locationManager = (LocationManager) mContext.getSystemService(mContext.LOCATION_SERVICE);
            LocationListener l = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {

                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            } ;
            // getting GPS status
            boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            // getting network status
            boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (!isGPSEnabled) {

            } else if (!isGPSEnabled && !isNetworkEnabled) {
            } else {
                this.canGetLocation = true;

                if (isGPSEnabled) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES,l);
                        Log.d("GPS Enabled", "GPS Enabled");
                        if (locationManager != null) {

                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                            }
                        }
                    }
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("error in location sensor");
            e.printStackTrace();
        }
        return location;
    }
}