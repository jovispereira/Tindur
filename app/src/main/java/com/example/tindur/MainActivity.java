package com.example.tindur;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tindur.services.ClimateAPI;
import com.example.tindur.utils.Utils;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity implements LocationListener {
    protected LocationManager locationManager;
    protected LocationListener locationListener;

    protected String clima = "";


    public void makeRequest(View view) {
        AsyncTask.execute(() -> {
            ClimateAPI climateAPI = new ClimateAPI("");
            Log.i("api", "Tabom eae ?");
        });
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utils utils = new Utils(getBaseContext());

        utils.locationPermissionRequest.launch(new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        });
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);

    }

    @Override
    public void onLocationChanged(Location location) {
        if(clima.equals("")) {

            clima = "Opa";

        } else {
            System.out.println("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
            System.out.println(clima);
        }

    }

    @Override
    public void onFlushComplete(int requestCode) {
        LocationListener.super.onFlushComplete(requestCode);

    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude", "disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude", "enable");
    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude", "status");
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

}