package com.example.grabfood.Helper;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.grabfood.Customer.Rating.ReviewActivity;
import com.example.grabfood.R;
import com.example.grabfood.Shipper.OrderDetailActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class OrderFragment extends Fragment {

    private static final int PERMISSIONS_REQUEST = 1;
    private final String TAG = "CustomerMapFragment";

    CountDownTimer Timer;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationRequest locationRequest;
    FragmentManager fragmentManager;

    public OrderFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        if (container == null){
            return null;
        }

        Log.e(TAG, "onCreateView");

        View view = inflater.inflate(R.layout.fragment_order, container, false);
        Button button = (Button) view.findViewById(R.id.orderButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrderDetail();
            }
        });
        callPermission();

        TextView edtTime = (TextView) view.findViewById(R.id.etaShipper);
        Timer = new CountDownTimer(11000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String time = String.format("%02dm%02ds",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
                Log.e(TAG, time);
//                String time = String.valueOf(millisUntilFinished/1000);
                edtTime.setText("Thời gian dự kiến: " + time);
            }

            @Override
            public void onFinish() {

                edtTime.setText("Arrived!");
                initDialog();
            }
        };
        Timer.start();

        return view;
    }

    private void initDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle("Đánh giá đơn hàng");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        goToRatingActivity();
                    }
                });
        alertDialog.show();
    }

    private void goToRatingActivity() {
        Intent intent = new Intent(getActivity(), ReviewActivity.class);
        startActivity(intent);
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