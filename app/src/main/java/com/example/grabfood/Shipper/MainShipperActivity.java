package com.example.grabfood.Shipper;
import com.example.grabfood.R;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;

import java.util.ArrayList;

public class MainShipperActivity extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST = 1;
    private final String TAG = "MainShipper";

    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationRequest locationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_shipper);

        Button button = (Button) findViewById(R.id.orderButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrderDetail();
            }
        });
        callPermission();


    }

    private void requestLocationUpdate(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PermissionChecker.PERMISSION_GRANTED
        && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PermissionChecker.PERMISSION_GRANTED) {
            fusedLocationProviderClient = new FusedLocationProviderClient(this);
            locationRequest = new LocationRequest();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setFastestInterval(2000);
            locationRequest.setInterval(4000);

            fusedLocationProviderClient.requestLocationUpdates(locationRequest,
                    new LocationCallback() {
                        @Override
                        public void onLocationResult(LocationResult locationResult) {
                            super.onLocationResult(locationResult);
                            Log.e(TAG, "lat: "+ locationResult.getLastLocation().getLatitude() +
                                    "long: " + locationResult.getLastLocation().getLongitude());
                            Log.d("@@@@", "before go in deep first");
                            setMarker(locationResult);

                        }

                        private void setMarker(LocationResult locationResult) {
                            MapsFragment mapFragment = (MapsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
                            Log.d("@@@@", "before go in deep" + mapFragment);
                            try {
                                double lat = locationResult.getLastLocation().getLatitude();
                                double lng = locationResult.getLastLocation().getLongitude();
                                mapFragment.setMarker(locationResult);
                            }
                            catch (NullPointerException e) {
                                Log.d("@@@@", "null pointer");
                                Log.e(TAG, "lat: "+ locationResult.getLastLocation().getLatitude() +
                                        "long: " + locationResult.getLastLocation().getLongitude());
                            }
                        }
                    }, getMainLooper());
        }
        else callPermission();
    }


    private void callPermission(){
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        String rationale = "Please provide location permission so that you can ...";
        Permissions.Options options = new Permissions.Options()
                .setRationaleDialogTitle("location permission")
                .setSettingsDialogTitle("Warning");

        Permissions.check(this/*context*/, permissions, rationale, options, new PermissionHandler() {
            @Override
            public void onGranted() {
                // do your task.
                requestLocationUpdate();
            }

            @Override
            public void onDenied(Context context, ArrayList<String> deniedPermissions) {
                // permission denied, block the feature.
                super.onDenied(context, deniedPermissions);
                callPermission();
            }
        });
    }


    private void openOrderDetail() {
        Intent intent = new Intent(this, OrderDetailActivity.class);
        startActivity(intent);
    }
}