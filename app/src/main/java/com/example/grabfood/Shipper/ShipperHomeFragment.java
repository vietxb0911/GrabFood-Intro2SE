package com.example.grabfood.Shipper;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.grabfood.Helper.MapsFragment;
import com.example.grabfood.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;

import java.util.ArrayList;


public class ShipperHomeFragment extends Fragment {

    private static final int PERMISSIONS_REQUEST = 1;
    private final String TAG = "ShipperHomeFragment";

    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationRequest locationRequest;
    FragmentManager fragmentManager;

    View view;
    public ShipperHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (container == null){
            return null;
        }

        Bundle args = getArguments();
        int index = args.getInt("flag", 0);
        Log.e(TAG, "onCreateView, index: "+ String.valueOf(index));
        switch (index){
            case 1:
                view = inflater.inflate(R.layout.activity_maps_shipper, container, false);
                Button button = (Button) view.findViewById(R.id.orderButton);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openOrderDetail();
                    }
                });
                callPermission();
                break;
            default:
                view = inflater.inflate(R.layout.shipper_homepage_fragment, container, false);
                break;
        }

        return view;
    }



    private void requestLocationUpdate(){
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PermissionChecker.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                == PermissionChecker.PERMISSION_GRANTED) {
            fusedLocationProviderClient = new FusedLocationProviderClient(getActivity());
            locationRequest = new LocationRequest();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setFastestInterval(2000);
            locationRequest.setInterval(4000);
            fragmentManager =  this.getChildFragmentManager();
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


//                            Log.d("@@@@", "before go in deep" + mapFragment);
                            try {
                                MapsFragment mapFragment = (MapsFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment);
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
                    }, getActivity().getMainLooper());
        }
        else callPermission();
    }

    private void callPermission(){
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        String rationale = "Please provide location permission so that you can ...";
        Permissions.Options options = new Permissions.Options()
                .setRationaleDialogTitle("location permission")
                .setSettingsDialogTitle("Warning");

        Permissions.check(getActivity(), permissions, rationale, options, new PermissionHandler() {
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
        Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
        startActivity(intent);
    }

}