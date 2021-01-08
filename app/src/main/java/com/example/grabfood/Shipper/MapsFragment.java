package com.example.grabfood.Shipper;
import com.example.grabfood.R;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;


public class MapsFragment extends Fragment {

    private static final int PERMISSIONS_REQUEST = 1;
    private Marker marker, res_marker, cus_marker;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng res_addr = new LatLng(10.7702844,106.6809951);
            res_marker = googleMap.addMarker(new MarkerOptions().position(res_addr).title("Marker in Restaurant"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(res_addr));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(res_addr, 16));
            LatLng cus_addr = new LatLng(10.7625383,106.6793707);
            cus_marker = googleMap.addMarker(new MarkerOptions().position(cus_addr).title("Marker in Customer"));
//            setPolylines(res_addr, cus_addr);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }



    public void setPolylines(LatLng coor1, LatLng coor2)
    {
        if (marker != null)
            marker.remove();
        Log.d("@@@@", "check marker");
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        Log.d("@@@@", "init map fragment");
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Polyline polyline1 = googleMap.addPolyline(new PolylineOptions()
                        .clickable(true)
                        .add(coor1, coor2));
            }
        });
    }

    public void setMarker(LocationResult locationResult)
    {
        double lat, lng;
        lat = locationResult.getLastLocation().getLatitude();
        lng = locationResult.getLastLocation().getLongitude();
        Log.d("@@@@", "begin");
        if (marker != null)
            marker.remove();
        Log.d("@@@@", "check marker");
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        Log.d("@@@@", "init map fragment");
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                LatLng latLng = new LatLng(lat, lng);
                MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("Im here");
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
                marker = googleMap.addMarker(markerOptions);
            }
        });
    }
}